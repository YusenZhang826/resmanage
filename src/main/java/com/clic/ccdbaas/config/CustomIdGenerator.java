package com.clic.ccdbaas.config;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.clic.ccdbaas.utils.uuid.IdUtils;
import org.springframework.stereotype.Component;

@Component
public class CustomIdGenerator implements IdentifierGenerator {
    @Override
    public Number nextId(Object entity) {
        return IdUtils.generatedNextId(entity);
    }

    @Override
    public String nextUUID(Object entity) {
        return IdUtils.generatedUUID();
    }
}
