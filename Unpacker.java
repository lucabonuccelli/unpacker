package lb.mycode.samples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Sample class created for Digital TSeam interview
 * 
 * @author lb
 *
 */
public class Unpacker {

	/**
	 * unpack a list of nested lists of integers <br>
	 * 
	 * 
	 * @param list
	 *            list of nested lists of integers
	 * @return a list of integer
	 * @throws NumberFormatException
	 *             at least an element is not a Integer
	 * @author lb
	 * 
	 * 
	 */
	public static List<Integer> unpack(List<Object> list)
			throws NumberFormatException {
		List<Integer> _r = new ArrayList<Integer>();
		LinkedList<Object> _s = new LinkedList<>(list);
		while (!_s.isEmpty()) {// // there are element to be evalueted
			Object e = _s.pop(); // next element
			if (e instanceof List<?>) {// element is a list
				_s.addAll(0, (List<?>) e);// list unpacked and do evaluation again
			} else {
				if (e instanceof Integer) {// element is an Integer,
					_r.add((Integer) e); // add unpacked element to result list
				} else {// element is something else: throw Ex!
					throw new NumberFormatException(
							"The list (or inner lists) contains not Integer or List element:\n"+
							e.getClass().toGenericString()+"\n"+
							e.toString());
				}
			}
		}
		return _r;
	}

	public static List<Object> pack(Object... args) {
		
		
		return Arrays.asList(args);
	}

	public static void main(String[] args) {
		System.out.println("welcome");
		
		
		List<Object> list = pack(
				0,
				1,
				pack((pack(pack(1, 2))), 4,
						pack(pack(5, 6), 7, pack(8, pack(9)))));

		System.out.println("original" + list);

		System.out.println("result" + unpack(list));
		
		System.out.println("test malformed input");
		
		List<Object> malformedlist = pack("I am a string",
				0,
				1,
				pack((pack(pack(1, 2))), 4,
						pack(pack(5, 6), 7, pack(8, pack(9)))));
		
		try {
			unpack(malformedlist);		
		} catch (NumberFormatException ex) {
			
			System.out.println("Exception catched!! illegal argument present");
			ex.printStackTrace(System.out);
			System.out.println("about above text : Exception catched!! illegal argument present");
			
		}
	

		
		for (int i=0; i<10;i++){
		System.out.println("throutput "+ randomTest(list)+"element/ms");
		}
		
		

	}
/**
 * test method
 * @param list
 */
	private static float randomTest(List<Object> list) {
		long t;
	System.out.println("generating huge input ....");
		Random r = new Random();
		int max = r.nextInt(10)+10; // be carefull about your heap! 
		for (int i = 1; i < max ; i++) {
			if (r.nextBoolean()) {
				list = pack(list);
			} else

				list = pack(list,
						r.nextInt(),
						r.nextInt(),
						pack(pack(pack(r.nextInt(), r.nextInt()))));
								
		if (r.nextBoolean()) {
			list= pack(list,0,list);
		}
		}

		System.out.println("Input:");
		System.out.println(list);

		t = System.currentTimeMillis();
		System.out.println("huge list unpacking");
		List<Integer> result = unpack(list);
		System.out.println(result);
		long  deltat= System.currentTimeMillis() - t;
		System.out.println("done, " + result.size() + " elements in "
				+ deltat + "ms");
		
		
		return ((float)result.size())/((float)deltat);
		
	}
}
