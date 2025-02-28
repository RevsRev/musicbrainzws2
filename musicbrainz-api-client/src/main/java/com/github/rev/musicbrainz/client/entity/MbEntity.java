package com.github.rev.musicbrainz.client.entity;

/**
 * Represents an entity served by the MusicBrainz API.
 */
public abstract class MbEntity {

    /**
     * @return The name of this for use when building API endpoints.
     */
    public abstract String getApiName();

    /**
     * Annotation.
     */
    public static final class MbAnnotation extends MbEntity {
        @Override
        public String getApiName() {
            return "annotation";
        }
    }

    /**
     * Area.
     */
    public static final class MbArea extends MbEntity {
        @Override
        public String getApiName() {
            return "area";
        }
    }

    /**
     * Artist.
     */
    public static final class MbArtist extends MbEntity {
        @Override
        public String getApiName() {
            return "artist";
        }
    }

    /**
     * CDStub.
     */
    public static final class CdStub extends MbEntity {
        @Override
        public String getApiName() {
            return "cdstub";
        }
    }

    /**
     * Event.
     */
    public static final class MbEvent extends MbEntity {
        @Override
        public String getApiName() {
            return "event";
        }
    }

    /**
     * Genre.
     */
    public static final class MbGenre extends MbEntity {
        @Override
        public String getApiName() {
            return "genre";
        }
    }

    /**
     * Instrument.
     */
    public static final class MbInstrument extends MbEntity {
        @Override
        public String getApiName() {
            return "instrument";
        }
    }

    /**
     * Label.
     */
    public static final class MbLabel extends MbEntity {
        @Override
        public String getApiName() {
            return "label";
        }
    }

    /**
     * Place.
     */
    public static final class MbPlace extends MbEntity {
        @Override
        public String getApiName() {
            return "place";
        }
    }

    /**
     * Recording.
     */
    public static final class MbRecording extends MbEntity {
        @Override
        public String getApiName() {
            return "recording";
        }
    }

    /**
     * Release.
     */
    public static final class MbRelease extends MbEntity {
        @Override
        public String getApiName() {
            return "release";
        }
    }

    /**
     * Release Group.
     */
    public static final class MbReleaseGroup extends MbEntity {
        @Override
        public String getApiName() {
            return "release-group";
        }
    }

    /**
     * Series.
     */
    public static final class MbSeries extends MbEntity {
        @Override
        public String getApiName() {
            return "series";
        }
    }

    /**
     * Tag.
     */
    public static final class MbTag extends MbEntity {
        @Override
        public String getApiName() {
            return "tag";
        }
    }

    /**
     * Work.
     */
    public static final class MbWork extends MbEntity {
        @Override
        public String getApiName() {
            return "work";
        }
    }

    /**
     * Url.
     */
    public static final class MbUrl extends MbEntity {
        @Override
        public String getApiName() {
            return "url";
        }
    }
}
