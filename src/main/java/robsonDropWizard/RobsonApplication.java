package robsonDropWizard;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;

import org.eclipse.jetty.servlets.CrossOriginFilter;

import com.robson.drop.health.AppHealthCheck;
import com.robson.drop.resources.BillsInfoResource;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class RobsonApplication extends Application<RobsonConfiguration> {

    public static void main(final String[] args) throws Exception 
    {
        new RobsonApplication().run(args);
    }

    @Override
    public String getName() 
    {
        return "testDrop";
    }

    @Override
    public void initialize(final Bootstrap<RobsonConfiguration> bootstrap) 
    {
    	bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(
                        bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)
                )
        );
    }

    @Override
    public void run(final RobsonConfiguration configuration, final Environment environment) 
    {
    	final FilterRegistration.Dynamic cors = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
    	cors.setInitParameter("allowedOrigins", "*");
    	cors.setInitParameter("allowedHeaders", "X-Requested-With, Content-Type, Accept, Origin");
    	cors.setInitParameter("allowedMethods", "OPTIONS, GET, PUT, POST, DELETE, HEAD");
    	cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    	final BillsInfoResource resource = new BillsInfoResource("","");
    	final AppHealthCheck healthCheck = new AppHealthCheck("");
    	environment.healthChecks().register("template", healthCheck);
    	environment.jersey().register(resource);
    }

}
