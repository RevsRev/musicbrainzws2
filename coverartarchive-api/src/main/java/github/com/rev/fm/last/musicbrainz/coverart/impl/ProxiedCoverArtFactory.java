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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import github.com.rev.fm.last.musicbrainz.coverart.CoverArt;

class ProxiedCoverArtFactory {

  private final DefaultCoverArtArchiveClient client;
  private final ObjectMapper mapper;

  public ProxiedCoverArtFactory(DefaultCoverArtArchiveClient client) {
    this.client = client;
    mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  public CoverArt valueOf(String json) throws JsonProcessingException {
    if (json == null || json.isEmpty()) {
      return null;
    }
    CoverArtBean coverArtBean = mapper.readValue(json, CoverArtBean.class);
    return new CoverArtBeanDecorator(coverArtBean, client);
  }
}
