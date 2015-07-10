package go.euro.data;

/**
 * INFO: String fields are used for simplicity.
 */
public class GeoPosition {
	
	private String latitude;
	private String longitude;
	
	public GeoPosition(String latitude, String longitude) {
		this.latitude = latitude;
		this.longitude = longitude;		
	}
	
	public String getLatitude() {
		return latitude;
	}
	
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	public String getLongitude() {
		return longitude;
	}
	
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	@Override
	public String toString() {
		return String.format("GeoPosition [lat=%s, lon=%s]", longitude, latitude);
	}
}
