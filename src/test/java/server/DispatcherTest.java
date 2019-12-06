package server;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class DispatcherTest {
    private String[] commandLineArgs = {"6666"};
    private MockDispatcher mockDispatcher;
    private int portNumber;
    private MockServerSocketWrapper mockServerSocketWrapper;
    private MockSocketWrapper mockSocketWrapper;

    @Before
    public void setUp() {
        portNumber = Integer.parseInt(commandLineArgs[0]);
        mockServerSocketWrapper = new MockServerSocketWrapper(portNumber);
        mockDispatcher = new MockDispatcher(mockServerSocketWrapper);
        mockSocketWrapper = new MockSocketWrapper();
    }

    @Test
    public void startServerCallsCreateAndListen() throws IOException {
        mockDispatcher.listenAndDispatch(commandLineArgs, mockServerSocketWrapper);
        Assert.assertTrue(mockServerSocketWrapper.getIsListening());
    }

    @Test
    public void aNewSocketWrapperListens() {
        mockServerSocketWrapper.createAndListen(portNumber);
        boolean result = mockServerSocketWrapper.getIsListening();
        Assert.assertTrue(result);
    }

    @Test
    public void aServerSocketAcceptsConnections() {
        MockSocketWrapper subject = mockServerSocketWrapper.acceptConnection();
        Assert.assertNotNull(subject);
    }

    @Test
    public void aServerSocketReceivesData() {
        String result = mockSocketWrapper.receive();
        Assert.assertEquals("test message", result);
    }

    @Test
    public void aServerSocketSendsData() {
        String message = mockSocketWrapper.receive();
        mockSocketWrapper.send(message);
        Assert.assertEquals(mockSocketWrapper.getSentData(), "TEST MESSAGE");
    }

    @Test
    public void aServerSocketCanClose() {
        mockSocketWrapper.close();
        Assert.assertTrue(mockSocketWrapper.getCloseWasCalled());
    }

}