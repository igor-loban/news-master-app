package by.balon.newsmaster.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class NewsMasterInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{ NewsMasterConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{ "/api/v1/*" };
    }
}
