package org.apereo.cas.pm.web.flow.actions;

import lombok.SneakyThrows;
import org.apereo.cas.util.junit.ConditionalIgnore;
import org.apereo.cas.util.junit.RunningContinuousIntegrationCondition;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletContext;
import org.springframework.webflow.context.servlet.ServletExternalContext;
import org.springframework.webflow.test.MockRequestContext;

import static org.junit.Assert.*;

/**
 * This is {@link SendPasswordResetInstructionsActionTests}.
 *
 * @author Misagh Moayyed
 * @since 5.3.0
 */
@ConditionalIgnore(condition = RunningContinuousIntegrationCondition.class, port = 25000)
public class SendPasswordResetInstructionsActionTests extends BasePasswordManagementActionTests {

    @Test
    @SneakyThrows
    public void verifyAction() {
        final var context = new MockRequestContext();
        final var request = new MockHttpServletRequest();
        request.addParameter("username", "casuser");
        context.setExternalContext(new ServletExternalContext(new MockServletContext(), request, new MockHttpServletResponse()));
        assertEquals("success", sendPasswordResetInstructionsAction.execute(context).getId());
    }
}