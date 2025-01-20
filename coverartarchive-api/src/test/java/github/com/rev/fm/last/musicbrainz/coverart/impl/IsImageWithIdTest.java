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

import github.com.rev.fm.last.musicbrainz.coverart.CoverArtImage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.function.Predicate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IsImageWithIdTest {

  private static final long ID = 123;

  private final Predicate<CoverArtImage> predicate = new IsImageWithId(ID);
  @Mock
  private CoverArtImage coverArtImage;

  @Test
  public void imageWithCorrectIdReturnsTrue() {
    when(coverArtImage.getId()).thenReturn(ID);
    assertThat(predicate.test(coverArtImage), is(true));
  }

  @Test
  public void imageWithDifferentIdReturnsFalse() {
    when(coverArtImage.getId()).thenReturn(3L);
    assertThat(predicate.test(coverArtImage), is(false));
  }
}
