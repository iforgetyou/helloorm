package com.zy17.controller.jsonview;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 13-6-18 Time: 下午1:49 To
 * change this template use File | Settings | File Templates.
 */
public class ViewAwareJsonMessageConverter extends
        MappingJacksonHttpMessageConverter {

    public ViewAwareJsonMessageConverter() {
        super();
        ObjectMapper defaultMapper = new ObjectMapper();
        defaultMapper.configure(
                SerializationConfig.Feature.DEFAULT_VIEW_INCLUSION, true);
        defaultMapper
                .setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
        setObjectMapper(defaultMapper);
    }

    @Override
    protected void writeInternal(Object object, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        if (object instanceof DataView && ((DataView) object).hasView()) {
            writeView((DataView) object, outputMessage);
        } else {
            super.writeInternal(object, outputMessage);
        }
    }

    protected void writeView(DataView view, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        JsonEncoding encoding = getJsonEncoding(outputMessage.getHeaders()
                .getContentType());
        ObjectMapper mapper = getMapperForView(view.getView());
        JsonGenerator jsonGenerator = mapper.getJsonFactory()
                .createJsonGenerator(outputMessage.getBody(), encoding);
        try {
            mapper.writeValue(jsonGenerator, view.getData());
        } catch (IOException ex) {
            throw new HttpMessageNotWritableException("Could not write JSON: "
                    + ex.getMessage(), ex);
        }
    }

    private ObjectMapper getMapperForView(Class<?> view) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationConfig.Feature.DEFAULT_VIEW_INCLUSION,
                false);
        mapper.setSerializationConfig(mapper.getSerializationConfig().withView(
                view));
        return mapper;
    }

}
