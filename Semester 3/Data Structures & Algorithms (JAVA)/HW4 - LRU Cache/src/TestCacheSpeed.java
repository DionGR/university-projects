
import components.LRUCache;
import components.interfaces.Cache;
import file_handling.DataSource;
import file_handling.WorkloadReader;

import java.io.IOException;


public class TestCacheSpeed {

	public static void main(String[] args) throws IOException {
		long avg = 0;
		int times = 10;
		for(int i = 0; i < times; i++) {
			int cachesize = 500;
			//initialize with your cache implementation
			Cache<String, String> cache = new LRUCache<>(cachesize);
			
			//give path to the dat file
			String dataFile = "datasets/dataset-5000/data-5000.dat";
			
			//give path to the workload file
			String requestsFile = "datasets/dataset-5000/requests-100000.dat";
			
			DataSource dataSource = new DataSource(dataFile);
			WorkloadReader requestReader = new WorkloadReader(requestsFile);
		
			String key = null;
			long numberOfRequests = 0;
			
			/*start performance test*/
		
			//track current time
			long startTime = System.currentTimeMillis();
			
			while ((key = requestReader.nextRequest()) != null) {
				System.out.println("requests " + numberOfRequests++);
				String data = (String) cache.lookUp(key);
				if (data == null) {//data not in cache
					data = dataSource.readItem(key);
					if (data == null) {
						throw new IllegalArgumentException("DID NOT FIND DATA WITH KEY " + key + ". Have you set up files properly?");
					} else {
						cache.store(key, data);
					}
				}
			}
			
			/*speed test finished*/
			long duration = System.currentTimeMillis() - startTime;
			
			System.out.printf("Read %d items in %d ms\n", numberOfRequests, duration);
			System.out.printf("Stats: lookups %d, hits %d, hit-ratio %f\n", cache.getNumberOfLookUps(), cache.getHits(), cache.getHitRatio());
			
			
			avg += duration;
			requestReader.close();
		}
		System.out.println("Average time: " + avg/times);
	}
}
