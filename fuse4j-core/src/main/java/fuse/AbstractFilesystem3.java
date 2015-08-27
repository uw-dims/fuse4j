package fuse;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.BufferOverflowException;

/**
 * @author Stuart Maclean
 *
 * AbstractFilesystem3 : an 'empty' implementation of the Filesystem3
 * interface.  Also implements XAttrSupport and LifecycleSupport.
 *
 * Useful in both test cases and as a building block for 'real'
 * filesystems that nevertheless don't need ALL the functionality on
 * offer.
 *
 */

public class AbstractFilesystem3 implements Filesystem3,
											XattrSupport, LifecycleSupport {

	// Start: Empty XattrSupport
	@Override
	public int getxattr(String path, String name,
						ByteBuffer dst, int position)
		throws FuseException, BufferOverflowException {

		return 0;
    }
	
	@Override
    public int setxattr(String path, String name, ByteBuffer value,
						int flags, int position) throws FuseException {
        return 0;
    }

	@Override
    public int listxattr(String path, XattrLister lister)
		throws FuseException {

		return 0;
    }

	@Override
    public int removexattr(String path, String name) throws FuseException {
		return 0;
    }
	
	@Override
    public int getxattrsize(String path, String name,
							FuseSizeSetter sizeSetter) throws FuseException {
		return 0;
	}
	// End: Empty XattrSupport

	// Start: Empty LifecycleSupport
	@Override
	public int init() {
		return 0;
	}

	@Override
	public int destroy() {
		return 0;
	}
	// End: Empty LifecycleSupport
	

	
	@Override
	public int getattr(String path, FuseGetattrSetter getattrSetter)
		throws FuseException {
		return Errno.ENOENT;
	}
	
	@Override
	public int readlink(String path, CharBuffer link) throws FuseException {
		return 0;
	}
	
	@Override
	public int getdir(String path, FuseDirFiller dirFiller)
		throws FuseException {
		return Errno.ENOENT;
	}
	
	@Override
	public int mknod(String path, int mode, int rdev) throws FuseException {
		return 0;
	}

	@Override
	public int mkdir(String path, int mode) throws FuseException {
		return 0;
	}

	@Override
	public int unlink(String path) throws FuseException {
		return 0;
	}
	
	@Override
	public int rmdir(String path) throws FuseException {
		return 0;
	}

	@Override
	public int symlink(String from, String to) throws FuseException {
		return 0;
	}
	
	@Override
	public int rename(String from, String to) throws FuseException {
		return 0;
	}

	@Override
	public int link(String from, String to) throws FuseException {
		return 0;
	}

	@Override
	public int chmod(String path, int mode) throws FuseException {
		return 0;
	}
	
	@Override
	public int chown(String path, int uid, int gid) throws FuseException {
		return 0;
	}

	@Override
	public int truncate(String path, long size) throws FuseException {
		return 0;
	}

	@Override
	public int utime(String path, int atime, int mtime) throws FuseException {
		return 0;
	}
	
	@Override
	public int statfs(FuseStatfsSetter statfsSetter) throws FuseException {
		return 0;
	}
	
   // if open returns a filehandle by calling FuseOpenSetter.setFh() method, it will be passed to every method that supports 'fh' argument
	@Override
   public int open(String path, int flags, FuseOpenSetter openSetter)
	   throws FuseException {
	   return Errno.ENOENT;
   }

   // fh is filehandle passed from open
	@Override
   public int read(String path, Object fh, ByteBuffer buf, long offset)
	   throws FuseException {
	   return 0;
   }
	
   // fh is filehandle passed from open,
   // isWritepage indicates that write was caused by a writepage
	@Override
	public int write(String path, Object fh, boolean isWritepage,
					 ByteBuffer buf, long offset) 
		throws FuseException {
		return 0;
	}
	

   // called on every filehandle close, fh is filehandle passed from open
	@Override
	public int flush(String path, Object fh) throws FuseException {
		return 0;
	}
	

	// called when last filehandle is closed, fh is filehandle passed from open
	@Override
	public int release(String path, Object fh, int flags) throws FuseException{
		return 0;
	}

   // Synchronize file contents, fh is filehandle passed from open,
   // isDatasync indicates that only the user data should be flushed, not the meta data
	@Override
   public int fsync(String path, Object fh, boolean isDatasync)
	   throws FuseException {
	   return 0;
   }
}

// eof
