package com.github.rev.musicbrainz.client.search.result;

import com.github.rev.musicbrainz.client.MbResult;
import com.github.rev.musicbrainz.client.entity.MbEntity;
import lombok.Getter;
import lombok.Setter;
import org.musicbrainz.ns.mmd_2.SeriesList;

import java.util.Date;

/**
 * Represents the result of searching/browsing/looking-up a Series.
 */
@Getter @Setter
public final class MbSeriesResult extends MbResult<MbEntity.MbSeries> {
    private Date created;
    private SeriesList seriesList;
}
