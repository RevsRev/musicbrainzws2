package github.com.rev.musicbrainz.model.entity.listelement;

import github.com.rev.musicbrainz.model.entity.RecordingWs2;
import github.com.rev.musicbrainz.wsxml.element.ListElement;

import java.util.ArrayList;
import java.util.List;


/**
 * A list of Recordings
 */
public class RecordingListWs2 extends ListElement {

    private List<RecordingWs2> recordings
            = new ArrayList<RecordingWs2>();

    /**
     * @return the recordings
     */
    public List<RecordingWs2> getRecordings() {
        return recordings;
    }

    /**
     * @param recordings the recordings to set
     */
    public void setRecordings(List<RecordingWs2> recordings) {
        this.recordings = recordings;
    }


    /**
     * Adds a track to the list.
     * <p>
     * It will also create and set new ArrayList if
     * {@link #recordings} is null.
     *
     * @param track The track to add
     */
    public void addRecording(RecordingWs2 recording) {
        if (recordings == null) {
            recordings = new ArrayList<RecordingWs2>();
        }

        recordings.add(recording);
    }

    public void addAllRecordings(List<RecordingWs2> recordingList) {
        if (recordings == null) {
            recordings = new ArrayList<RecordingWs2>();
        }

        recordings.addAll(recordingList);
    }
}
