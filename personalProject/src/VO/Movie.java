package VO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Movie {
	private String movieName;
	private String movieDate;
	private String movieGenre;
	private String movieNation;
	private String movieKeyword;

	@Override
	public String toString() {
		return "Movie [movieName=" + movieName + ", movieDate=" + movieDate + ", movieGenre=" + movieGenre
				+ ", movieNation=" + movieNation + "]";
	}
	
}
