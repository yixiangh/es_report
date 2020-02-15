package com.seari.es_report.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Map;

@Component
public class JedisUtils {
    private Logger logger = LoggerFactory.getLogger(JedisUtils.class);

    @Autowired
    private JedisPool jedisPool;

    /**
     * 设置过期时间
     * @param key
     */
    public void setString(String key,String value){
        try(Jedis jedis = jedisPool.getResource()){
            jedis.set(key,value);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.toString());
        }
    }

    /**
     * 通过key获取储存在redis中的value
     * 并释放连接
     * @param key
     * @return 成功返回value 失败返回null
     */
    public String getString(String key) {
        String value = null;
        /// try()写法，不需要手动调用close方法，会自动释放连接
        try (Jedis jedis = jedisPool.getResource()){
            value = jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
        }
        return value;
    }

    /**
     * 删除指定的key,也可以传入一个包含key的数组
     *
     * @param keys 一个key 也可以使 string 数组
     * @return 返回删除成功的个数
     */
    public Long del(String... keys) {
        try (Jedis jedis = jedisPool.getResource()){
            return jedis.del(keys);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return 0L;
        }
    }

    /**
     * 通过key向指定的value值追加值
     * @param key
     * @param value
     */
    public void appendString(String key,String value){
        try(Jedis jedis=jedisPool.getResource()){
            jedis.append(key,value);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.toString());
        }
    }

    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    public boolean existKey(String key){
        boolean h = false;
        try(Jedis jedis=jedisPool.getResource()){
            h = jedis.exists(key);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.toString());
        }
        return h;
    }

    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    public boolean existKey(byte[] key){
        boolean h = false;
        try(Jedis jedis=jedisPool.getResource()){
            h = jedis.exists(key);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.toString());
        }
        return h;
    }

    /**
     * 清空当前数据库中的所有 key,此命令从不失败
     */
    public void clearDb(){
        try(Jedis jedis=jedisPool.getResource()){
            jedis.flushDB();
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.toString());
        }
    }

    /**
     * 设置过期时间
     * @param key
     * @param sec 单位：秒
     */
    public void setString(String key,String value,int sec){
        try(Jedis jedis = jedisPool.getResource()){
            if(sec>0){
                jedis.setex(key,sec,value);
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.toString());
        }
    }

    /**
     * 获取指定key的剩余有效时间
     * @param key
     * @return 当 key 不存在时，返回 -2 。当 key 存在但没有设置剩余生存时间时，返回 -1 。否则，以秒为单位，返回 key
     *         的剩余生存时间。 发生异常 返回 0
     */
    public long activeSeconds(String key){
        long sec = 0;
        try(Jedis jedis = jedisPool.getResource()){
            sec = jedis.ttl(key);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.toString());
        }
        return sec;
    }

    /**
     * 设置map缓存
     * @param key
     * @param val
     * @param sec
     */
    public <T> void setMap(String key, Map<String,T> val, int sec){
        try(Jedis jedis = jedisPool.getResource()){
            if(sec>0){
                jedis.setex(key.getBytes(),sec, ObjectTransUtils.serialize(val));
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.toString());
        }
    }

    /**
     * 获取map集合
     * @param key
     * @return
     */
    public <T> Map<String,T> getMap(String key){
        Map<String,T> res = null;
        try(Jedis jedis = jedisPool.getResource()){
            if(existKey(key.getBytes())){
                byte[] val = jedis.get(key.getBytes());
                res = (Map<String,T>) ObjectTransUtils.deserialize(val);
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.toString());
        }
        return res;
    }

    /**
     * 设置list缓存
     * @param key
     * @param value
     * @param  sec 有效期   单位：秒
     */
    public <T> void setList(String key, List<T> value, int sec){
        try(Jedis jedis = jedisPool.getResource()){
            if(sec>0){
                jedis.setex(key.getBytes(),sec, ObjectTransUtils.serialize(value));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 获取集合 list
     * @param key
     * @param <T>
     * @return List<T>
     */
    public <T> List<T> getList(String key){
        List<T> res = null;
        try(Jedis jedis = jedisPool.getResource()){
            if(existKey(key.getBytes())){
                byte[] val = jedis.get(key.getBytes());
                res = (List<T>) ObjectTransUtils.deserialize(val);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }


}
