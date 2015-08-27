package fuse;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Stuart Maclean
 *
 * FuseMountTest.
 *
 * @see FuseMount
 */

public class FuseMountTest extends junit.framework.TestCase {

	/*
	  Once this runs, the test case will be blocked until you issue,
	  in a separate terminal:

	  $ fusermount -u mount
	*/
	public void test1() throws Exception {
		AbstractFilesystem3 fs = new AbstractFilesystem3();
	
		File mnt = new File( "mount" );
		mnt.mkdirs();
		mnt.deleteOnExit();
		String[] args = { mnt.getName(), "-f" };

		Log l = LogFactory.getLog( fs.getClass() );

		System.out.println( "***" );
		System.out.println
			( "Mounting a FUSE filesystem.  To umount: 'fusermount -u " +
			  mnt.getName() + "'." );
		System.out.println( "Only then will this test case complete." );
		System.out.println( "***" );

		FuseMount.mount( args, fs, l );
	} 
}

// eof