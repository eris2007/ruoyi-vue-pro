package cn.iocoder.yudao.framework.signature.config;

import cn.iocoder.yudao.framework.redis.config.YudaoRedisAutoConfiguration;
import cn.iocoder.yudao.framework.signature.core.aop.ApiSignatureAspect;
import cn.iocoder.yudao.framework.signature.core.redis.ApiSignatureRedisDAO;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * HTTP API 签名的自动配置类
 *
 * @author Zhougang
 */
@AutoConfiguration(after = YudaoRedisAutoConfiguration.class)
public class YudaoApiSignatureAutoConfiguration {

    /**
     * 配置ApiSignatureAspect Bean，用于处理API签名校验的逻辑
     *
     * @param signatureRedisDAO Api签名信息存储的Redis数据访问对象
     * @return 返回配置好的ApiSignatureAspect实例
     */
    @Bean
    public ApiSignatureAspect signatureAspect(ApiSignatureRedisDAO signatureRedisDAO) {
        return new ApiSignatureAspect(signatureRedisDAO);
    }

    @Bean
    public ApiSignatureRedisDAO signatureRedisDAO(StringRedisTemplate stringRedisTemplate) {
        return new ApiSignatureRedisDAO(stringRedisTemplate);
    }

}
