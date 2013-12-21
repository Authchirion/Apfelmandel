package de.sonnenschein.org;

public class MandelCalcBaseParams
{
	public final static MandelCalcBaseParams DEFAULT_PARAMS = new MandelCalcBaseParams(-2.0f, 1.0f, -1.5f, 1.5f);
	
	MandelCalcBaseParams(float rmin, float rmax, float imin, float imax)
	{
		this.REAL_MAX = rmax;
		this.REAL_MIN = rmin;
		this.IMAG_MIN = imin;
		this.IMAG_MAX = imax;
		this.INC = REAL_MAX - REAL_MIN;
	}
	
	public final float REAL_MIN;
	public final float REAL_MAX;
	
	public final float IMAG_MIN;
	public final float IMAG_MAX;
	
	public final float INC; 
}
