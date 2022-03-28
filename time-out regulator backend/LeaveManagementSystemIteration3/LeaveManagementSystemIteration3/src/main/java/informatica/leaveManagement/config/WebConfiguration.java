package Project.leaveManagement.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

@Configuration
//@EnableWebMvc
public class WebConfiguration implements WebMvcConfigurer {

	public MappingJackson2HttpMessageConverter jacksonMessageConverter(){
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();

        Hibernate5Module hibernate5Module = new Hibernate5Module();
	    hibernate5Module.disable(Hibernate5Module.Feature.USE_TRANSIENT_ANNOTATION);
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.registerModule(hibernate5Module);
	    mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
	    mapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
        messageConverter.setObjectMapper(mapper);
        return messageConverter;

    }
	
	//public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(jacksonMessageConverter());
        //super.configureMessageConverters(converters);
	}
}
