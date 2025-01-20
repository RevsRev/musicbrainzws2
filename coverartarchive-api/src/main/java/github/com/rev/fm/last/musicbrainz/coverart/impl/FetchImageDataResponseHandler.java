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

import github.com.rev.fm.last.musicbrainz.coverart.util.HttpUtil;
import org.apache.hc.client5.http.HttpResponseException;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;


enum FetchImageDataResponseHandler implements HttpClientResponseHandler<InputStream> {
  /* */
  INSTANCE;

  @Override
  public InputStream handleResponse(ClassicHttpResponse response) throws IOException {
    HttpEntity entity = response.getEntity();

    if (response.getCode() == HttpStatus.SC_OK) {
      InputStream content = entity.getContent();
      return new BufferedInputStream(content);
    }
    HttpUtil.consumeEntity(entity);
    throw new HttpResponseException(response.getCode(), response.getReasonPhrase());
  }
}
