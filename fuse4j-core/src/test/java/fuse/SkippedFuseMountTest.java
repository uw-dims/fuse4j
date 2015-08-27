package fuse;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Stuart Maclean
 *
 * SkippedFuseMountTest.
 *
 * Experience shows that this test will need to be the ONLY test class
 * run.  Otherwise, some other test case which successfully loads the
 * native library may leave that library loaded, and this test class
 * then fails, since it first forces a native library load skip and
 * then EXPECTS an UnsatisfiedLinkError.  Seems as if surefire plugin
 * uses one VM only for all test classes??
 *
 * To summarise, run this
 *
 * $ mvn test -Dtest=SkippedFuseMountTest (or some other test)
 *
 * instead of this
 *
 * $ mvn test
 */

public class SkippedFuseMountTest extends junit.framework.TestCase {
 
	/*
	  We wish to force a skip of the native code loading, which we can
	  do via System property setting. Then even on platforms where we
	  do have the .so file (Linux 32,64 bit) we can make it appear
	  that those .so files are not available.
	*/
	public void testSkipNativeLoad() throws Exception {
		AbstractFilesystem3 fs = new AbstractFilesystem3();
	
		File mnt = new File( "mount" );
		mnt.mkdirs();
		String[] args = { mnt.getName(), "-f" };

		Log l = LogFactory.getLog( fs.getClass() );

		/*
		  Set system property 'PREFIX.LIBNAME.disabled', where PREFIX,
		  LIBNAME are defined in pom.xml and transmitted down to
		  platform-dependent Makefile(s).  The NativeLoader.load
		  call then made by FuseMount will see this property set
		  and skip trying to locate and load the native library.
		*/
		System.setProperty( "fuse.fuse4j-core.disabled", "" );
		try {
			FuseMount.mount( args, fs, l );
			fail( "Expected UnsatisfiedLinkError" );
		} catch( UnsatisfiedLinkError ule ) {
		}
	}
}

// eof