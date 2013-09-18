package com.zy17.controller.jsonview;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.HandlerMethodReturnValueHandlerComposite;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 13-6-18 Time: 下午1:48 To
 * change this template use File | Settings | File Templates.
 */
public class JsonViewSupportFactoryBean implements InitializingBean {

	@Autowired
	private RequestMappingHandlerAdapter adapter;

	@Override
	public void afterPropertiesSet() throws Exception {
		HandlerMethodReturnValueHandlerComposite returnValueHandlers = adapter
				.getReturnValueHandlers();
		List<HandlerMethodReturnValueHandler> handlers = Lists
				.newArrayList(returnValueHandlers.getHandlers());
		decorateHandlers(handlers);
		adapter.setReturnValueHandlers(handlers);
	}

	private void decorateHandlers(List<HandlerMethodReturnValueHandler> handlers) {
		for (HandlerMethodReturnValueHandler handler : handlers) {
			if (handler instanceof RequestResponseBodyMethodProcessor) {
				ViewInjectingReturnValueHandler decorator = new ViewInjectingReturnValueHandler(
						handler);
				int index = handlers.indexOf(handler);
				handlers.set(index, decorator);
				break;
			}
		}
	}

}
