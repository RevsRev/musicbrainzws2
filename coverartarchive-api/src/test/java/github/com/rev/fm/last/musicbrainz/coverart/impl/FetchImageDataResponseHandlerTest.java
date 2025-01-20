/**
 * Copyright (C) ${license.git.copyrightYears} Last.fm
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package github.com.rev.fm.last.musicbrainz.coverart.impl;

import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpException;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FetchImageDataResponseHandlerTest {

  private static final byte[] DATA = new byte[] { 123 };

  @Mock
  private ClassicHttpResponse response;

  @Mock
  private HttpEntity entity;

  private final HttpClientResponseHandler<InputStream> handler = FetchImageDataResponseHandler.INSTANCE;

  @Before
  public void initialise() throws IllegalStateException, IOException {
    InputStream inputStream = new ByteArrayInputStream(DATA);
    when(response.getCode()).thenReturn(HttpStatus.SC_OK);
    when(entity.getContent()).thenReturn(inputStream);
  }

  @Test
  public void inputStreamIsReturnedWhenImageExists() throws IOException, HttpException {
    when(response.getEntity()).thenReturn(entity);
    InputStream actual = handler.handleResponse(response);
    assertThat(actual.readAllBytes(), is(DATA));
  }

  @Test(expected = IOException.class)
  public void ioExceptionIsThrownWhenRequestCannotBeFulfilled() throws IOException, HttpException {
    when(response.getCode()).thenReturn(HttpStatus.SC_NOT_FOUND);
    InputStream stream = handler.handleResponse(response);
  }

}
