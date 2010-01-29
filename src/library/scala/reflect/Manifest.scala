/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2007-2010, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

// $Id$


package scala.reflect

import scala.runtime._
import scala.collection.mutable._
import scala.collection.immutable.{List, Nil}

/** <p>
  *   A <code>Manifest[T]</code> is an opaque descriptor for type <code>T</code>.
  *   Currently, its only use is to give access to the erasure of the type as a
  *   <code>Class</code> instance.
  * </p>
  * <p>
  *   <b>BE AWARE</b>: The different type-relation operators are all forwarded
  *   to the erased type as an approximation of the final semantics where
  *   these operators should be on the unerased type.
  * </p>
  */
@serializable
trait Manifest[T] extends ClassManifest[T] {
  override def typeArguments: List[Manifest[_]] = List()

  override def arrayManifest: Manifest[Array[T]] =
    Manifest.classType[Array[T]](arrayClass[T](erasure))
}

@serializable
trait AnyValManifest[T] extends Manifest[T] {
  import Manifest.{ Any, AnyVal }
  override def <:<(that: ClassManifest[_]): Boolean = (that eq this) || (that eq Any) || (that eq AnyVal)
  override def equals(that: Any): Boolean = this eq that.asInstanceOf[AnyRef]
  override def hashCode = System.identityHashCode(this)
}

/** <ps>
  *   This object is used by the compiler and <b>should not be used in client
  *   code</b>. The object <code>Manifest</code> defines factory methods for
  *   manifests.
  * </p>
  * <p>
  *   <b>BE AWARE</b>: The factory for refinement types is missing and
  *   will be implemented in a later version of this class.
  * </p>
  */
object Manifest {
  val Byte: AnyValManifest[Byte] = new (AnyValManifest[Byte] @serializable) {
    def erasure = java.lang.Byte.TYPE
    override def toString = "Byte"
    override def newArray(len: Int): Array[Byte] = new Array[Byte](len)
    override def newWrappedArray(len: Int): WrappedArray[Byte] = new WrappedArray.ofByte(new Array[Byte](len))
    override def newArrayBuilder(): ArrayBuilder[Byte] = new ArrayBuilder.ofByte()
    private def readResolve(): Any = Manifest.Byte
  }

  val Short: AnyValManifest[Short] = new (AnyValManifest[Short] @serializable) {
    def erasure = java.lang.Short.TYPE
    override def toString = "Short"
    override def newArray(len: Int): Array[Short] = new Array[Short](len)
    override def newWrappedArray(len: Int): WrappedArray[Short] = new WrappedArray.ofShort(new Array[Short](len))
    override def newArrayBuilder(): ArrayBuilder[Short] = new ArrayBuilder.ofShort()
    private def readResolve(): Any = Manifest.Short
  }

  val Char: AnyValManifest[Char] = new (AnyValManifest[Char] @serializable) {
    def erasure = java.lang.Character.TYPE
    override def toString = "Char"
    override def newArray(len: Int): Array[Char] = new Array[Char](len)
    override def newWrappedArray(len: Int): WrappedArray[Char] = new WrappedArray.ofChar(new Array[Char](len))
    override def newArrayBuilder(): ArrayBuilder[Char] = new ArrayBuilder.ofChar()
    private def readResolve(): Any = Manifest.Char
  }

  val Int: AnyValManifest[Int] = new (AnyValManifest[Int] @serializable) {
    def erasure = java.lang.Integer.TYPE
    override def toString = "Int"
    override def newArray(len: Int): Array[Int] = new Array[Int](len)
    override def newWrappedArray(len: Int): WrappedArray[Int] = new WrappedArray.ofInt(new Array[Int](len))
    override def newArrayBuilder(): ArrayBuilder[Int] = new ArrayBuilder.ofInt()
    private def readResolve(): Any = Manifest.Int
  }

  val Long: AnyValManifest[Long] = new (AnyValManifest[Long] @serializable) {
    def erasure = java.lang.Long.TYPE
    override def toString = "Long"
    override def newArray(len: Int): Array[Long] = new Array[Long](len)
    override def newWrappedArray(len: Int): WrappedArray[Long] = new WrappedArray.ofLong(new Array[Long](len))
    override def newArrayBuilder(): ArrayBuilder[Long] = new ArrayBuilder.ofLong()
    private def readResolve(): Any = Manifest.Long
  }

  val Float: AnyValManifest[Float] = new (AnyValManifest[Float] @serializable) {
    def erasure = java.lang.Float.TYPE
    override def toString = "Float"
    override def newArray(len: Int): Array[Float] = new Array[Float](len)
    override def newWrappedArray(len: Int): WrappedArray[Float] = new WrappedArray.ofFloat(new Array[Float](len))
    override def newArrayBuilder(): ArrayBuilder[Float] = new ArrayBuilder.ofFloat()
    private def readResolve(): Any = Manifest.Float
  }

  val Double: AnyValManifest[Double] = new (AnyValManifest[Double] @serializable) {
    def erasure = java.lang.Double.TYPE
    override def toString = "Double"
    override def newArray(len: Int): Array[Double] = new Array[Double](len)
    override def newWrappedArray(len: Int): WrappedArray[Double] = new WrappedArray.ofDouble(new Array[Double](len))
    override def newArrayBuilder(): ArrayBuilder[Double] = new ArrayBuilder.ofDouble()
    private def readResolve(): Any = Manifest.Double
  }

  val Boolean: AnyValManifest[Boolean] = new (AnyValManifest[Boolean] @serializable) {
    def erasure = java.lang.Boolean.TYPE
    override def toString = "Boolean"
    override def newArray(len: Int): Array[Boolean] = new Array[Boolean](len)
    override def newWrappedArray(len: Int): WrappedArray[Boolean] = new WrappedArray.ofBoolean(new Array[Boolean](len))
    override def newArrayBuilder(): ArrayBuilder[Boolean] = new ArrayBuilder.ofBoolean()
    private def readResolve(): Any = Manifest.Boolean
  }

  val Unit: AnyValManifest[Unit] = new (AnyValManifest[Unit] @serializable) {
    def erasure = java.lang.Void.TYPE
    override def toString = "Unit"
    override def newArray(len: Int): Array[Unit] = new Array[Unit](len)
    override def newWrappedArray(len: Int): WrappedArray[Unit] = new WrappedArray.ofUnit(new Array[Unit](len))
    override def newArrayBuilder(): ArrayBuilder[Unit] = new ArrayBuilder.ofUnit()
    private def readResolve(): Any = Manifest.Unit
  }

  val Any: Manifest[Any] = new ClassTypeManifest[Any](None, classOf[java.lang.Object], List()) {
    override def toString = "Any"
    override def <:<(that: ClassManifest[_]): Boolean = (that eq this)
    override def equals(that: Any): Boolean = this eq that.asInstanceOf[AnyRef]
    override def hashCode = System.identityHashCode(this)
    private def readResolve(): Any = Manifest.Any
  }

  val Object: Manifest[Object] = new ClassTypeManifest[Object](None, classOf[java.lang.Object], List()) {
    override def toString = "Object"
    override def <:<(that: ClassManifest[_]): Boolean = (that eq this) || (that eq Any)
    override def equals(that: Any): Boolean = this eq that.asInstanceOf[AnyRef]
    override def hashCode = System.identityHashCode(this)
    private def readResolve(): Any = Manifest.Object
  }

  val AnyVal: Manifest[AnyVal] = new ClassTypeManifest[AnyVal](None, classOf[java.lang.Object], List()) {
    override def toString = "AnyVal"
    override def <:<(that: ClassManifest[_]): Boolean = (that eq this) || (that eq Any)
    override def equals(that: Any): Boolean = this eq that.asInstanceOf[AnyRef]
    override def hashCode = System.identityHashCode(this)
    private def readResolve(): Any = Manifest.AnyVal
  }

  val Null: Manifest[Null] = new ClassTypeManifest[Null](None, classOf[java.lang.Object], List()) {
    override def toString = "Null"
    override def <:<(that: ClassManifest[_]): Boolean =
      (that ne null) && (that ne Nothing) && !(that <:< AnyVal)
    override def equals(that: Any): Boolean = this eq that.asInstanceOf[AnyRef]
    override def hashCode = System.identityHashCode(this)
    private def readResolve(): Any = Manifest.Null
  }

  val Nothing: Manifest[Nothing] = new ClassTypeManifest[Nothing](None, classOf[java.lang.Object], List()) {
    override def toString = "Nothing"
    override def <:<(that: ClassManifest[_]): Boolean = (that ne null)
    override def equals(that: Any): Boolean = this eq that.asInstanceOf[AnyRef]
    override def hashCode = System.identityHashCode(this)
    private def readResolve(): Any = Manifest.Nothing
  }

  /** Manifest for the singleton type `value.type'. */
  def singleType[T <: AnyRef](value: AnyRef): Manifest[T] =
    new (Manifest[T] @serializable) {
      /** Note - this was doing a type match on value to exclude AnyVal, which does not work.
       *  Pattern matching _: AnyRef matches everything because of boxing.
       */
      lazy val erasure = value.getClass
      override lazy val toString = value.toString + ".type"
    }

  /** Manifest for the class type `clazz[args]', where `clazz' is
    * a top-level or static class.
    * @note This no-prefix, no-arguments case is separate because we
    *       it's called from ScalaRunTime.boxArray itself. If we
    *       pass varargs as arrays into this, we get an infinitely recursive call
    *       to boxArray. (Besides, having a separate case is more efficient)
    */
  def classType[T](clazz: Predef.Class[_]): Manifest[T] =
    new ClassTypeManifest[T](None, clazz, Nil)

  /** Manifest for the class type `clazz', where `clazz' is
    * a top-level or static class and args are its type arguments. */
  def classType[T](clazz: Predef.Class[T], arg1: Manifest[_], args: Manifest[_]*): Manifest[T] =
    new ClassTypeManifest[T](None, clazz, arg1 :: args.toList)

  /** Manifest for the class type `clazz[args]', where `clazz' is
    * a class with non-package prefix type `prefix` and type arguments `args`.
    */
  def classType[T](prefix: Manifest[_], clazz: Predef.Class[_], args: Manifest[_]*): Manifest[T] =
    new ClassTypeManifest[T](Some(prefix), clazz, args.toList)

  /** Manifest for the class type `clazz[args]', where `clazz' is
    * a top-level or static class. */
  @serializable
  private class ClassTypeManifest[T](prefix: Option[Manifest[_]],
                                     val erasure: Predef.Class[_],
                                     override val typeArguments: List[Manifest[_]]) extends Manifest[T] {
    override def toString =
      (if (prefix.isEmpty) "" else prefix.get.toString+"#") +
      (if (erasure.isArray) "Array" else erasure.getName) +
      argString
   }

  def arrayType[T](arg: Manifest[_]): Manifest[Array[T]] =
    arg.asInstanceOf[Manifest[T]].arrayManifest

  /** Manifest for the abstract type `prefix # name'. `upperBound' is not
    * strictly necessary as it could be obtained by reflection. It was
    * added so that erasure can be calculated without reflection. */
  def abstractType[T](prefix: Manifest[_], name: String, clazz: Predef.Class[_], args: Manifest[_]*): Manifest[T] =
    new (Manifest[T] @serializable) {
      def erasure = clazz
      override val typeArguments = args.toList
      override def toString = prefix.toString+"#"+name+argString
    }

  /** Manifest for the abstract type `prefix # name'. `upperBound' is not
    * strictly necessary as it could be obtained by reflection. It was
    * added so that erasure can be calculated without reflection.
    * todo: remove after next bootstrap
    */
  def abstractType[T](prefix: Manifest[_], name: String, upperbound: ClassManifest[_], args: Manifest[_]*): Manifest[T] =
    new (Manifest[T] @serializable) {
      def erasure = upperbound.erasure
      override val typeArguments = args.toList
      override def toString = prefix.toString+"#"+name+argString
    }

  /** Manifest for the intersection type `parents_0 with ... with parents_n'. */
  def intersectionType[T](parents: Manifest[_]*): Manifest[T] =
    new (Manifest[T] @serializable) {
      def erasure = parents.head.erasure
      override def toString = parents.mkString(" with ")
    }
}
