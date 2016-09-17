package be.swsb.jaxrs.test;

import org.assertj.core.api.AbstractAssert;

import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Objects;

public class ResponseAssert extends AbstractAssert<ResponseAssert, Response> {

    private ResponseAssert(Response actual, Class<?> selfType) {
        super(actual, selfType);
    }

    @SuppressWarnings("WeakerAccess") //public to provide an alternative when conflict with statically imported ResponseAssertions.assertThat
    public static ResponseAssert assertThat(Response actual) {
        return new ResponseAssert(actual, ResponseAssert.class);
    }

    public ResponseAssert hasStatus(Response.StatusType expectedStatus) {
        isNotNull();

        if (!Objects.equals(actual.getStatusInfo(), expectedStatus)) {
            failWithMessage("Expected status code to be <%s> but was <%s>", expectedStatus, actual.getStatusInfo());
        }
        return this;
    }

    public ResponseAssert hasLocation(URI expectedURI) {
        isNotNull();

        if (!Objects.equals(actual.getLocation(), expectedURI)) {
            failWithMessage("Expected status code to be <%s> but was <%s>", expectedURI, actual.getLocation());
        }
        return this;
    }
}
