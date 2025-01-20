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

import github.com.rev.fm.last.musicbrainz.coverart.CoverArt;
import github.com.rev.fm.last.musicbrainz.coverart.CoverArtImage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


class CoverArtBeanDecorator implements CoverArt {

  private final CoverArtBean delegate;
  private final DefaultCoverArtArchiveClient client;
  private final List<github.com.rev.fm.last.musicbrainz.coverart.CoverArtImage> coverArtImages = new ArrayList<>();

  public CoverArtBeanDecorator(CoverArtBean delegate, DefaultCoverArtArchiveClient client) {
    this.delegate = delegate;
    this.client = client;
  }

  @Override
  public List<CoverArtImage> getImages() {
    return getProxiedCoverArtImages();
  }

  @Override
  public String getMusicBrainzReleaseUrl() {
    return delegate.getRelease();
  }

  @Override
  public CoverArtImage getImageById(long id) {
    return getImageOrNull(new IsImageWithId(id));
  }

  @Override
  public CoverArtImage getFrontImage() {
    return getImageOrNull(IsFrontImage.INSTANCE);
  }

  @Override
  public CoverArtImage getBackImage() {
    return getImageOrNull(IsBackImage.INSTANCE);
  }

  private CoverArtImage getImageOrNull(Predicate<CoverArtImage> filter) {
    Collection<CoverArtImage> filtered = getProxiedCoverArtImages().stream().filter(filter).collect(Collectors.toSet());
    if (filtered.isEmpty()) {
      return null;
    }
    return filtered.iterator().next();
  }

  private List<CoverArtImage> getProxiedCoverArtImages() {
    if (coverArtImages.isEmpty()) {
      for (CoverArtImageBean image : delegate.getImages()) {
        coverArtImages.add(new ProxiedCoverArtImageBeanDecorator(image, client));
      }
    }
    return coverArtImages;
  }

}
