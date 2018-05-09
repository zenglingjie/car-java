package qfx;
import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import qfx.common.config.PropertyConfig;
import qfx.common.listener.ApplicationEnvironmentPreparedEventListener;
import qfx.common.listener.ApplicationFailedEventListener;
import qfx.common.listener.ApplicationPreparedEventListener;
import qfx.common.listener.ApplicationStartedEventListener;
@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass=true)
@EnableMethodCache(basePackages = "qfx.service")
@EnableCreateCacheAnnotation
@EnableConfigurationProperties({PropertyConfig.class})
@MapperScan("qfx.mapper")
public class WebApplication extends SpringBootServletInitializer {
  public static void main(String[] args) {
    configureApplication(new SpringApplicationBuilder()).run(args);
    //SpringApplication.run(WebApplication.class, args);
  }
  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    return configureApplication(builder);
  }
  private static SpringApplicationBuilder configureApplication(SpringApplicationBuilder builder) {
    ApplicationListener<?> listeners[]=new ApplicationListener[]{new ApplicationStartedEventListener(), new ApplicationEnvironmentPreparedEventListener(), new ApplicationFailedEventListener(),
            new ApplicationPreparedEventListener(),};
    return builder.sources(WebApplication.class).bannerMode(Banner.Mode.OFF).listeners(listeners);
  }
  /*@Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurerAdapter() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/strategy").allowedOrigins("http://localhost:8001");
      }
    };
  }*/
}
