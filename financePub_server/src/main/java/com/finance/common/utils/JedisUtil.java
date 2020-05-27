package com.finance.common.utils;

import com.google.common.collect.Sets;
import com.mysql.jdbc.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;

import java.util.*;

/**
 * Jedis工具类
 *
 * @author jsqfengbao@qq.com
 */
@Component
@Slf4j
public class JedisUtil {
    private int expireTime = 60* 60* 24;

    @Autowired
    private JedisPool jedisPool;

    /**
     * 获取缓存
     *
     * @param key 键
     * @return 值
     */
    public String get(String key) {
        String value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(key)) {
                value = jedis.get(key);
                value = org.apache.commons.lang.StringUtils.isNotBlank(value) && !"nil".equalsIgnoreCase(value) ? value : null;
                log.debug("get key:"+key+",value:"+value);
            }
        } catch (Exception e) {
            log.warn("get key:"+key+",exception:"+e);
        } finally {
            returnResource(jedis);
        }
        return value;
    }

    /**
     * 获取缓存
     *
     * @param key 键
     * @return 值
     */
    public Object getObject(String key) {
        Object value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(getBytesKey(key))) {
                value = toObject(jedis.get(getBytesKey(key)));
                log.debug("get key:"+key+",value:"+value);
            }
        } catch (Exception ignored) {
        } finally {
            returnResource(jedis);
        }
        return value;
    }

    public String set(String key, String value) {
        return set(key, value, expireTime);
    }

    /**
     * 设置缓存
     *
     * @param key          键
     * @param value        值
     * @param cacheSeconds 超时时间，0为不超时
     * @return result
     */
    public String set(String key, String value, int cacheSeconds) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.set(key, value);
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
            log.debug("set key:"+key+",value:"+value);
        } catch (Exception e) {
            log.warn("set key:"+key+",exception:"+e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 设置缓存过期时间
     *
     * @param key          键
     * @param cacheSeconds 超时时间，0为不超时
     */
    public void expire(String key, int cacheSeconds) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(getBytesKey(key))) {
                jedis.expire(key, cacheSeconds);
                log.debug("expire key:"+key+",cacheSeconds:"+cacheSeconds);
            }
        } catch (Exception e) {
            log.warn("expire key:"+key+",exception:"+e);
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 获取剩余过期时间
     *
     * @param key 键
     * @return
     */
    public Long ttl(String key) {
        Jedis jedis = null;
        Long ttlTime = 0L;
        try {
            jedis = getResource();
            if (jedis.exists(getBytesKey(key))) {
                ttlTime = jedis.ttl(key);
                log.debug("expire ttl:"+key);
            }
        } catch (Exception e) {
            log.warn("expire ttl:"+",exception:"+e);
        } finally {
            returnResource(jedis);
        }
        return ttlTime;
    }

    /**
     * 设置缓存
     *
     * @param key          键
     * @param value        值
     * @param cacheSeconds 超时时间，0为不超时
     * @return result
     */
    public String setObject(String key, Object value, int cacheSeconds) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.set(getBytesKey(key), toBytes(value));
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
        } catch (Exception ignored) {
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 设置缓存
     *
     * @param key   键
     * @param value 值
     * @return result
     */
    public String setObject(String key, Object value) {
        return setObject(key, value, expireTime);
    }

    /**
     * key筛选
     *
     * @param pattern 正则
     * @return result
     */
    public Set<String> keys(String pattern) {
        Set<String> result = Sets.newHashSet();
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.keys(pattern);
        } catch (Exception e) {
            log.warn("pattern:"+pattern+",exception:"+e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 获取List缓存
     *
     * @param key 键
     * @return 值
     */
    public List<String> getList(String key) {
        List<String> value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(key)) {
                value = jedis.lrange(key, 0, -1);
            }
        } catch (Exception e) {
            log.warn("getList:"+key+",exception:"+e);

        } finally {
            returnResource(jedis);
        }
        return value;
    }

    /**
     * 获取List缓存
     *
     * @param key 键
     * @return 值
     */
    public List<Object> getObjectList(String key) {
        List<Object> value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            byte[] keybyte = getBytesKey(key);
            if (jedis.exists(keybyte)) {
                List<byte[]> list = jedis.lrange(keybyte, 0, -1);
                value = new ArrayList<>();
                for (byte[] bs : list) {
                    value.add(toObject(bs));
                }
                log.debug("getObjectList:"+key+",value:"+value);
            }
        } catch (Exception e) {
            log.warn("getObjectList:"+key+",exception:"+e);
        } finally {
            returnResource(jedis);
        }
        return value;
    }

    /**
     * @param keyPrefix
     * @return List<Object>
     * @Description:根据key前缀模糊查询缓存对象
     * @date 2016年9月5日
     * @author zhuliyun
     */
    public List<Object> getObjectListByKeyPrefix(String keyPrefix) {
        List<Object> value = new ArrayList<>();
        Jedis jedis = null;
        try {
            jedis = getResource();
            Set<String> keys = getKeysByPrefix(keyPrefix);
            if (keys != null && keys.size() > 0) {
                for (String key : keys) {
                    Object object = getObject(key);
                    value.add(object);

                }

            }
        } catch (Exception e) {
            log.warn("getObjectList:"+keyPrefix+",exception:"+e);
        } finally {
            returnResource(jedis);
        }
        return value;
    }

    /**
     * 设置List缓存
     *
     * @param key          键
     * @param value        值
     * @param cacheSeconds 超时时间，0为不超时
     * @return
     */
    public long setList(String key, List<String> value, int cacheSeconds) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(key)) {
                jedis.del(key);
            }
            result = jedis.rpush(key, (String[]) value.toArray(new String[value.size()]));
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
            log.debug("setList:"+key+",value:"+value);
        } catch (Exception e) {
            log.warn("setList:"+key+",exception:"+e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 设置List缓存
     *
     * @param key          键
     * @param value        值
     * @param cacheSeconds 超时时间，0为不超时
     * @return
     */
    public long setObjectList(String key, List<Object> value,
                              int cacheSeconds) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(getBytesKey(key))) {
                jedis.del(key);
            }
            List<byte[]> list = new ArrayList<>();
            for (Object o : value) {
                list.add(toBytes(o));
            }
            result = jedis.rpush(getBytesKey(key), (byte[][]) list.toArray());
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
            log.debug("setObjectList:"+key+",value:"+value);
        } catch (Exception e) {
            log.warn("setObjectList:"+key+",exception:"+e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 向List缓存中添加值
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public long listAdd(String key, String... value) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.rpush(key, value);
            log.debug("listAdd : "+key+"value:"+value);
        } catch (Exception e) {
            log.warn("listAdd: "+key+"exception:"+e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 向List缓存中添加值
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public long listObjectAdd(String key, Object... value) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            List<byte[]> list = new ArrayList<>();
            for (Object o : value) {
                list.add(toBytes(o));
            }
            result = jedis.rpush(getBytesKey(key), (byte[][]) list.toArray());
            log.debug("listObjectAdd: "+ key+"value:"+ value);
        } catch (Exception e) {
            log.warn("listObjectAdd :"+ key+"exception:"+ e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 获取缓存
     *
     * @param key 键
     * @return 值
     */
    public Set<String> getSet(String key) {
        Set<String> value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(key)) {
                value = jedis.smembers(key);
                log.debug("getSet :"+ key+"value:"+ value);
            }
        } catch (Exception e) {
            log.warn("getSet: "+key+",exception:"+e);
        } finally {
            returnResource(jedis);
        }
        return value;
    }

    /**
     * @param keyPrefix
     * @return Set<String>
     * @Description:获取以指定开头前缀的所有Key
     * @date 2016年9月5日
     * @author zhuliyun
     */
    public Set<String> getKeysByPrefix(String keyPrefix) {
        Set<String> value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            value = jedis.keys(keyPrefix + "*");
            log.debug("getSet:"+keyPrefix+",value:"+ value);
        } catch (Exception e) {
            log.warn("getSet:"+keyPrefix+",exception:"+ e);
        } finally {
            returnResource(jedis);
        }
        return value;

    }

    /**
     * 获取缓存
     *
     * @param key 键
     * @return 值
     */
    public Set<Object> getObjectSet(String key) {
        Set<Object> value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(getBytesKey(key))) {
                value = new HashSet<Object>();
                Set<byte[]> set = jedis.smembers(getBytesKey(key));
                for (byte[] bs : set) {
                    value.add(toObject(bs));
                }
                log.debug("getObjectSet:"+key+",value:"+value);
            }
        } catch (Exception e) {
            log.warn("getObjectSet:"+key+",exception:"+ e);
        } finally {
            returnResource(jedis);
        }
        return value;
    }

    /**
     * 设置Set缓存
     *
     * @param key          键
     * @param value        值
     * @param cacheSeconds 超时时间，0为不超时
     * @return
     */
    public long setSet(String key, Set<String> value, int cacheSeconds) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(key)) {
                jedis.del(key);
            }
            result = jedis.sadd(key, (String[]) value.toArray(new String[value.size()]));
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
            log.debug("setSet:"+key+",value:"+ value);
        } catch (Exception e) {
            log.warn("setSet:"+key+",exception:"+ e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 设置Set缓存
     *
     * @param key          键
     * @param value        值
     * @param cacheSeconds 超时时间，0为不超时
     * @return
     */
    public long setObjectSet(String key, Set<Object> value,
                             int cacheSeconds) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(getBytesKey(key))) {
                jedis.del(key);
            }
            Set<byte[]> set = new HashSet<byte[]>();
            for (Object o : value) {
                set.add(toBytes(o));
            }
            result = jedis.sadd(getBytesKey(key), (byte[][]) set.toArray());
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
            log.debug("setObjectSet :"+key+",value:"+ value);
        } catch (Exception e) {
            log.warn("setObjectSet : "+ key+",exception:"+ e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 向Set缓存中添加值
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public long setSetAdd(String key, String... value) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.sadd(key, value);
            log.debug("setSetAdd :"+key+",value:"+value);
        } catch (Exception e) {
            log.warn("setSetAdd :"+key+",exception:"+e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 向Set缓存中添加值
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public long setSetObjectAdd(String key, Object... value) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            Set<byte[]> set = new HashSet<byte[]>();
            for (Object o : value) {
                set.add(toBytes(o));
            }
            result = jedis.rpush(getBytesKey(key), (byte[][]) set.toArray());
            log.debug("setSetObjectAdd :"+key+",value:"+value);
        } catch (Exception e) {
            log.warn("setSetObjectAdd :"+key+",exception:"+e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 获取Map缓存
     *
     * @param key 键
     * @return 值
     */
    public Map<String, String> getMap(String key) {
        Map<String, String> value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(key)) {
                value = jedis.hgetAll(key);
                log.debug("getMap :"+key+",value:"+value);
            }
        } catch (Exception e) {
            log.warn("getMap :"+key+",exception:"+e);
        } finally {
            returnResource(jedis);
        }
        return value;
    }

    /**
     * 获取Map缓存
     *
     * @param key 键
     * @return 值
     */
    public Map<String, Object> getObjectMap(String key) {
        Map<String, Object> value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(getBytesKey(key))) {
                value = new HashMap<>(1);
                Map<byte[], byte[]> map = jedis.hgetAll(getBytesKey(key));
                for (Map.Entry<byte[], byte[]> e : map.entrySet()) {
                    value.put(StringUtils.toString(e.getKey()),
                            toObject(e.getValue()));
                }
                log.debug("getObjectMap :"+key+",value:"+value);
            }
        } catch (Exception e) {
            log.warn("getObjectMap :"+key+",exception:"+e);
        } finally {
            returnResource(jedis);
        }
        return value;
    }

    /**
     * 设置Map缓存
     *
     * @param key          键
     * @param value        值
     * @param cacheSeconds 超时时间，0为不超时
     * @return
     */
    public String setMap(String key, Map<String, String> value,
                         int cacheSeconds) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(key)) {
                jedis.del(key);
            }
            result = jedis.hmset(key, value);
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
            log.debug("setMap :"+key+",value:"+value);
        } catch (Exception e) {
            log.warn("setMap :"+key+",exception:"+e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 设置Map缓存
     *
     * @param key          键
     * @param value        值
     * @param cacheSeconds 超时时间，0为不超时
     * @return
     */
    public String setObjectMap(String key, Map<String, Object> value,
                               int cacheSeconds) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(getBytesKey(key))) {
                jedis.del(key);
            }
            Map<byte[], byte[]> map = new HashMap<>(value.size());
            for (Map.Entry<String, Object> e : value.entrySet()) {
                map.put(getBytesKey(e.getKey()), toBytes(e.getValue()));
            }
            result = jedis.hmset(getBytesKey(key), (Map<byte[], byte[]>) map);
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
            log.debug("setObjectMap :"+key+",value:"+value);
        } catch (Exception e) {
            log.warn("setObjectMap :"+key+",exception:"+e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 向Map缓存中添加值
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public String mapPut(String key, Map<String, String> value) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.hmset(key, value);
            log.debug("mapPut :"+key+",value:"+value);
        } catch (Exception e) {
            log.warn("mapPut :"+key+",exception:"+e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 向Map缓存中添加值
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public String mapObjectPut(String key, Map<String, Object> value) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            Map<byte[], byte[]> map = new HashMap<>(value.size());
            for (Map.Entry<String, Object> e : value.entrySet()) {
                map.put(getBytesKey(e.getKey()), toBytes(e.getValue()));
            }
            result = jedis.hmset(getBytesKey(key), (Map<byte[], byte[]>) map);
            log.debug("mapObjectPut :"+key+",value:"+value);
        } catch (Exception e) {
            log.warn("mapObjectPut :"+key+",exception:"+e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 移除Map缓存中的值
     *
     * @param key    键
     * @param mapKey 值
     * @return
     */
    public long mapRemove(String key, String mapKey) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.hdel(key, mapKey);
            log.debug("mapRemove :"+key+",mapKey:"+mapKey);
        } catch (Exception e) {
            log.warn("mapRemove :"+key+",exception:"+e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 移除Map缓存中的值
     *
     * @param key    键
     * @param mapKey 值
     * @return
     */
    public long mapObjectRemove(String key, String mapKey) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.hdel(getBytesKey(key), getBytesKey(mapKey));
            log.debug("mapObjectRemove :"+key+",mapKey:"+mapKey);
        } catch (Exception e) {
            log.warn("mapObjectRemove :"+key+",exception:"+e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 判断Map缓存中的Key是否存在
     *
     * @param key    键
     * @param mapKey 值
     * @return
     */
    public boolean mapExists(String key, String mapKey) {
        boolean result = false;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.hexists(key, mapKey);
            log.debug("mapExists :"+key+",mapKey:"+mapKey);
        } catch (Exception e) {
            log.warn("mapExists :"+key+",exception:"+e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 判断Map缓存中的Key是否存在
     *
     * @param key    键
     * @param mapKey 值
     * @return
     */
    public boolean mapObjectExists(String key, String mapKey) {
        boolean result = false;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.hexists(getBytesKey(key), getBytesKey(mapKey));
            log.debug("mapObjectExists :"+key+",mapKey:"+mapKey);
        } catch (Exception e) {
            log.warn("mapObjectExists :"+key+",exception:"+e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 删除缓存
     *
     * @param key 键
     * @return
     */
    public long del(String key) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(key)) {
                result = jedis.del(key);
                log.debug("del :"+key);
            } else {
                log.debug("del "+key+" not exists");
            }
        } catch (Exception e) {
            log.warn("del :"+key+",exception:"+e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 删除缓存
     *
     * @param key 键
     * @return
     */
    public long delObject(String key) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(getBytesKey(key))) {
                result = jedis.del(getBytesKey(key));
                log.debug("delObject :"+key);
            } else {
                log.debug("delObject "+key+" not exists");
            }
        } catch (Exception e) {
            log.warn("delObject :"+key+",exception:"+e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 缓存是否存在
     *
     * @param key 键
     * @return
     */
    public boolean exists(String key) {
        boolean result = false;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.exists(key);
            log.debug("exists :"+key);
        } catch (Exception e) {
            log.warn("exists :"+key+",exception:"+e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 缓存是否存在
     *
     * @param key 键
     * @return
     */
    public boolean existsObject(String key) {
        boolean result = false;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.exists(getBytesKey(key));
            log.debug("existsObject :"+key);
        } catch (Exception e) {
            log.warn("existsObject :"+key+",exception:"+e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 获取资源
     *
     * @return
     * @throws JedisException
     */
    public Jedis getResource() throws JedisException {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
        } catch (JedisException e) {
            returnResource(jedis);
            throw e;
        } finally {
        }
        return jedis;
    }

    /**
     * 释放资源
     *
     * @param jedis
     */
    public void returnResource(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    /**
     * 获取byte[]类型Key
     *
     * @param object
     * @return
     */
    public byte[] getBytesKey(Object object) {
        if (object instanceof String) {
            return StringUtils.getBytes((String) object);
        } else {
            return ObjectUtils.serialize(object);
        }
    }

    /**
     * 获取byte[]类型Key
     *
     * @param key
     * @return
     */
    public Object getObjectKey(byte[] key) {
        try {
            return StringUtils.toString(key);
        } catch (UnsupportedOperationException uoe) {
            try {
                return toObject(key);
            } catch (UnsupportedOperationException uoe2) {
                uoe2.printStackTrace();
            }
        }
        return null;
    }

    public boolean isBlank(String str) {
        return str == null || "".equals(str.trim());
    }

    /**
     * Object转换byte[]类型
     *
     * @param object
     * @return
     */
    public byte[] toBytes(Object object) {
        return ObjectUtils.serialize(object);
    }

    /**
     * byte[]型转换Object
     *
     * @param bytes
     * @return
     */
    public Object toObject(byte[] bytes) {
        return ObjectUtils.unserialize(bytes);
    }

    /**
     * 删除缓存
     *
     * @param className  类名
     * @param methodName 方法名
     */
    public void delByClass(String className, String methodName) {
        String key = com.finance.common.utils.StringUtils.genKey("SYS_CACHE", className, methodName);

        del(key);
        delObject(key);
    }
}

