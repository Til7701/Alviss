package de.til7701.alviss.surefire_provider;

import org.apache.maven.surefire.api.provider.AbstractProvider;
import org.apache.maven.surefire.api.provider.ProviderParameters;
import org.apache.maven.surefire.api.provider.SurefireProvider;
import org.apache.maven.surefire.api.report.ReporterException;
import org.apache.maven.surefire.api.suite.RunResult;
import org.apache.maven.surefire.api.testset.TestSetFailedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Objects;

public class AlvissSurefireProvider extends AbstractProvider implements SurefireProvider {

    private static final Logger log = LoggerFactory.getLogger(AlvissSurefireProvider.class);

    private final ProviderParameters parameters;

    public AlvissSurefireProvider(ProviderParameters parameters) {
        this.parameters = parameters;
        log.error("Got ProviderParameters: {}", parameters);
    }

    @Override
    public Iterable<Class<?>> getSuites() {
        try {
            List<Class<?>> classes = List.of(Class.forName("de.til7701.alviss.surefire_provider.it.TestContract"));
            parameters.getReporterFactory().createTestReportListener().error(classes.toString());
            return classes;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public RunResult invoke(Object forkTestSet) throws TestSetFailedException, ReporterException, InvocationTargetException {
        parameters.getReporterFactory().createTestReportListener().error(Objects.toString(forkTestSet));
        return null;
    }

}
