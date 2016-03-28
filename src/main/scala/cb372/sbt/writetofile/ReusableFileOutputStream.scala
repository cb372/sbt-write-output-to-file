package cb372.sbt.writetofile

import java.io._

/**
 * A file output stream that can be reused after being closed.
 * If the stream is written to after `close()` is called,
 * it reopens the file and starts writing to it again.
 */
class ReusableFileOutputStream(file: File, append: Boolean) extends OutputStream {

  private var underlying: Option[FileOutputStream] = None

  override def write(b: Int): Unit = {
    getOrCreateUnderlying().write(b)
  }

  override def write(bs: Array[Byte]): Unit = {
    getOrCreateUnderlying().write(bs)
  }

  override def write(bs: Array[Byte], off: Int, len: Int): Unit = {
    getOrCreateUnderlying().write(bs, off, len)
  }

  override def flush(): Unit = {
    getOrCreateUnderlying().flush()
  }

  override def close(): Unit = {
    underlying.map(_.close())
    underlying = None
  }

  private[this] def getOrCreateUnderlying(): FileOutputStream = underlying match {
    case Some(stream) => stream
    case None =>
      val stream = new FileOutputStream(file, append)
      underlying = Some(stream)
      stream
  }

}
