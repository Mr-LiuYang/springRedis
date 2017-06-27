package springRedis.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import javax.sql.DataSource;

/**
 * Created by liuyang on 2017/6/22.
 */
public class RootConfig implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext context=new AnnotationConfigWebApplicationContext();
        context.register(MvcConfiguration.class);
        context.setServletContext(servletContext);
        context.getEnvironment().addActiveProfile("dev");
        context.register(DatabaseConfig.class);
        ServletRegistration.Dynamic dispatcher=servletContext.addServlet("dispatcher",new DispatcherServlet(context));
        dispatcher.addMapping("/");
//        可以配置文件上传
        dispatcher.setMultipartConfig(new MultipartConfigElement("d:/fileTemp",1024*1024*5,1024*1024*8,0));
        dispatcher.setLoadOnStartup(1);

//        FilterRegistration.Dynamic encodingFilter=servletContext.addFilter("encodingFilter",new CharacterEncodingFilter("UTF_8",true));
//        encodingFilter.setInitParameter("encoding","UTF_8");
//        encodingFilter.addMappingForUrlPatterns(null,false,"/");
    }
}
