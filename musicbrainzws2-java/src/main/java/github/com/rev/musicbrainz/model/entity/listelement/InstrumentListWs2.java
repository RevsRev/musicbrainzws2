package github.com.rev.musicbrainz.model.entity.listelement;

import java.util.ArrayList;
import java.util.List;

import github.com.rev.musicbrainz.model.entity.InstrumentWs2;
import github.com.rev.musicbrainz.wsxml.element.ListElement;


/**
 * A list of Instruments
 */
public class InstrumentListWs2 extends ListElement{

	private List<InstrumentWs2> instruments = new ArrayList<InstrumentWs2>();

	/**
	 * @return the instruments
	 */
	public List<InstrumentWs2> getInstruments() {
		return instruments;
	}

	/**
	 * @param instruments the instruments to set
	 */
	public void setInstruments(List<InstrumentWs2> instruments) {
		this.instruments = instruments;
	}

	public void addInstrument(InstrumentWs2 instrument) 
	{
		if (instruments == null) {
			instruments = new ArrayList<InstrumentWs2>();
		}
		
		instruments.add(instrument);
	}
           public void addAllInstruments(List<InstrumentWs2> instrumentList) 
	{
                if (instruments == null) {
                        instruments = new ArrayList<InstrumentWs2>();
                }

                instruments.addAll(instrumentList);
	}
}
