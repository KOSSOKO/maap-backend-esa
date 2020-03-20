package com.esa.bmap.service.catalogue.data.implement;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esa.bmap.model.Algorithm;
import com.esa.bmap.model.BmaapUser;
import com.esa.bmap.model.Privacy;
import com.esa.bmap.model.Status;
import com.esa.bmap.service.catalogue.algorithm.interfaces.AlgorithmServiceInterface;
import com.esa.bmap.service.catalogue.data.interfaces.InitDataBaseServiceInterface;
@Service(value = "InitDataBaseServiceInterface")
public class InitDataBaseServiceImpl implements InitDataBaseServiceInterface{
	Logger log = LoggerFactory.getLogger(InitDataBaseServiceInterface.class);
	
	@Autowired
	private AlgorithmServiceInterface algorithmService;
	



	/**
	 * set method : spring syntax
	 * @param algorithmService
	 */
	public void setAlgorithmService(AlgorithmServiceInterface algorithmService) {
		this.algorithmService = algorithmService;
	}


	/**
	 * load some Examples to the database 
	 * @return List<Algorithm>
	 */
	@Override
	public Collection<Algorithm> initDataBase() {
		//Create Algorithms
		Algorithm SlrToGrdProjGeometryRadar = new Algorithm("SlrToGrdProj", new BmaapUser(null, null), "Guinea Savanna", "3H:20m:3s", "http://localhost/bmap/RADAR/Geometry/SlrToGrdProj","http://localhost/bmap/RADAR/Geometry/SlrToGrdProj", "1.1.1", "Documentation for SlrToGrdProj algorithm",  Privacy.PUBLIC, Status.SHARED, "RADAR", "Geometry", "rada geometry teddy kossoko python",null);
		Algorithm filteringconversiont2dBRadiometryRadar = new Algorithm("filtering_conversiont2dB", new BmaapUser(null, null), "Rainforest", "5H:40m:15s", "http://localhost/bmap/RADAR/Radiometry/filtering_conversiont2dB", "http://localhost/bmap/RADAR/Geometry/SlrToGrdProj", "1.1.1", "Documentation for filtering_conversiont2dB algorithm", Privacy.PUBLIC, Status.SHARED, "RADAR", "Geometry", "rada geometry  filtering conversion teddy kossoko python", null);
		Algorithm slcToSig0RadiometryRadar = new Algorithm("SLC_toSig0", new BmaapUser(null, null), "Black Forest", "1H:10m:50s", "http://localhost/bmap/RADAR/Radiometry/SLC_toSig0", "http://localhost/bmap/RADAR/Geometry/SlrToGrdProj", "1.1.1", "Documentation for SLC_toSig0 algorithm", Privacy.PUBLIC, Status.SHARED, "RADAR", "Geometry", "rada geometry slc sig0 teddy kossoko python", null);
		
//		
//		try {
//			algorithmService.addAlgorithm(SlrToGrdProjGeometryRadar, geometryRadar.getId());
//			algorithmService.addAlgorithm(filteringconversiont2dBRadiometryRadar, radiometryRadar.getId());
//			algorithmService.addAlgorithm(slcToSig0RadiometryRadar, radiometryRadar.getId());
//		} catch (BmapException e) {
//			// TODO Auto-generated catch block
//			log.error(e.getMessage());
//		}
		
		return null;
	}

}
