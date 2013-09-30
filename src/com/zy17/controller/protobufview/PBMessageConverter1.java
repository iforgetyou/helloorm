package com.zy17.controller.protobufview;

import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.Message;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;

/**
 * pb对象转换，自动完成pb对象的序列化与反序列化，让controller的接口脱离这些重复工作
 * @author shannon
 * @time Dec 6, 2012 12:16:11 PM
 * @email shannonchou@126.com
 */
public class PBMessageConverter1 extends AbstractHttpMessageConverter<Message> {

    public PBMessageConverter1() {
        //设置该转换器支持的媒体类型
        super(new MediaType("application", "x-protobuf", Charset.forName("UTF-8")));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return Message.class.isAssignableFrom(clazz);
    }

    @Override
    protected Message readInternal(Class<? extends Message> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        try {
            Method newBuilder = clazz.getMethod("newBuilder");
            GeneratedMessage.Builder<?> builder = (GeneratedMessage.Builder<?>) newBuilder.invoke(clazz);
            return builder.mergeFrom(inputMessage.getBody()).build();
        } catch (Exception e) {
//            throw e;
            return null;
        }
    }

    @Override
    protected void writeInternal(Message message, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        outputMessage.getBody().write(message.toByteArray());
    }
//    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
//        return Message.class.isAssignableFrom(type);
//    }
//
//    public long getSize(Message m, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
//        return m.getSerializedSize();
//    }
//
//    public void writeTo(Message m, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String,Object> httpHeaders,OutputStream entityStream) throws IOException, WebApplicationException {
//        entityStream.write(m.toByteArray());
//    }
}