package com.tufire.seller.exception;

import com.tufire.common.exception.ValidationException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @author it001hyl
 * @date 2019-03-19
 */
@Configuration
public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            String message = Util.toString(response.body().asReader());
            JSONObject jsonObject = JSONObject.fromObject(message);
            String  causeMessage = jsonObject.getString("message");
            String messageleft =causeMessage.substring(0,2);
            if("ht".equals(messageleft)) {
                return new RuntimeException(causeMessage.substring(2,causeMessage.length()));
            }
            return new RuntimeException();
        } catch (IOException ignored) {
            return new RuntimeException();
        }

    }


}
