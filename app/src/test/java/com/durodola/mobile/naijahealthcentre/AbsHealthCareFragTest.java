package com.durodola.mobile.naijahealthcentre;

import com.durodola.mobile.naijahealthcentre.AbstractFragment.AbstractHealthFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by mobile on 2016-04-28.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({AbsHealthCareFragTest.class})
public class AbsHealthCareFragTest {

    @Mock
    AbstractHealthFragment MockabstractHealthFragment;

    @Before
    public void create() {
        initMocks(this);
        PowerMockito.mockStatic(AbstractHealthFragment.class);
    }

    @Test
    public void testConnection() {
        Mockito.when(MockabstractHealthFragment.isConnected()).thenReturn(true);
        assertEquals(MockabstractHealthFragment.isConnected(), true);
    }

    @Test
    public void testNoInternet() {
        Mockito.doNothing().when(PowerMockito.spy(MockabstractHealthFragment)).noNetworkAlert();

    }
}
