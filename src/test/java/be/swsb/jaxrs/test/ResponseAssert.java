package be.swsb.jaxrs.test;

import org.assertj.core.api.AbstractAssert;

import javax.ws.rs.core.Response;
import java.util.Objects;

public class ResponseAssert extends AbstractAssert<ResponseAssert, Response> {

    protected ResponseAssert(Response actual, Class<?> selfType) {
        super(actual, selfType);
    }

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
}
