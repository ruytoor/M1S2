#ifndef VECTOR3_H
#define VECTOR3_H


/**
@file
@author F. Aubert
@brief Vector3 operations (x,y,z)

*/

#include "Vector2.h"
#include "Vector4.h"
#include <vector>
#include <iostream>

namespace prog3d {

/**
@class Vector3
@brief Vector3 operations (x,y,z)
*/

class Vector2;
class Vector4;


template<class T> void swapIfMin(T *a,T *b) {T c;if (*b<*a) {c=*a;*a=*b;*b=c;}}


class Vector3
{

  double c[3];
  static float fc[3]; // for casting
  void cnew();
  public:
    /// @brief default constructor
    Vector3();
    /// @brief destructor
    virtual ~Vector3();

    /// @brief constructs the vector (x,y,z)
    Vector3(double x,double y,double z);
    /// @brief copy constructor
    Vector3(const Vector3 &t);
    /// @brief construct from a Vector2 (z=0).
    Vector3(const prog3d::Vector2 &t);

    /// @brief construct from 2 points (a,b) (the vector b-a)
    Vector3(const Vector3 &a,const Vector3 &b);

    /// @brief get the i-th coord (0 for x, 1 for y, 2 for z).
    inline double get(unsigned int coord) const {return c[coord];}

    /// @brief get X
    inline double x() const {return c[0];}
    /// @brief get Y
    inline double y() const {return c[1];}
    /// @brief get Z
    inline double z() const {return c[2];}

    /// @brief copy to this
    void set(const Vector3 &copy);
    /// @brief set the i-th co-ordinate (0 for x, 1 for y, 2 for z)
    void set(int i,double coordinate);

    /// @brief set this with b-a (a and b interpreted as points).
    void setVector(const Vector3 &a,const Vector3 &b);

    /// @brief set X
    inline void x(double k) {c[0]=k;}
    /// @brief set Y
    inline void y(double k) {c[1]=k;}
    /// @brief set Z
    inline void z(double k) {c[2]=k;}

    /// @brief get the float * casting of the vector (copy of (x,y,z)). Ex : glVertex3fv(p.fv())
    float *fv() const;
    /// @brief get a double * pointing to (x,y,z) (no copy). Ex : glVertex3dv(p.dv())
    const double *dv() const {return c;}

    /// @brief copy this to a
    void copyTo(Vector3 *a);

    /// @brief copy a to this.
    void copyFrom(const Vector3 &a);

    /// @brief Ex : p1+=p2
    Vector3 &operator+=(const Vector3 &a);
    /// @brief Ex : p1*=3.0;
    Vector3 &operator*=(double k);
    /// @brief Ex : p1/=3.0;
    Vector3 &operator/=(double k);
    /// @brief operator copy. Ex : p1=p2
    Vector3& operator=(const Vector3 &a);
    /// @brief Ex : p=p1+p2
    friend Vector3 operator +(const Vector3 &a,const Vector3 &b);
    /// @brief Ex : p=p1-p2
    friend Vector3 operator -(const Vector3 &a,const Vector3 &b);
    /// @brief Ex : p1=3.0*p2
    friend Vector3 operator *(double k,const Vector3 &b);
    /// @brief Ex : p1=p2*3.0
    friend Vector3 operator *(const Vector3 &b,double k);
    /// @brief Ex : p1=p2/3.0
    friend Vector3 operator /(const Vector3 &b,double k);
    /// @brief Ex : p1=-p2
    friend Vector3 operator -(const Vector3 &a);


    /// @brief set x,y,z
    void set(double x,double y,double z);
    /// @brief add (x,y,z) to this
    void add(double x,double y,double z);
    /// @brief add a to this. Ex : p.add(a)
    void add(const Vector3 &a);
    /// @brief substract a from this. Ex : p.sub(a)
    void sub(const Vector3 &a);

    /// @brief set this with a+b
    void add(const Vector3 &a,const Vector3 &b);
    /// @brief set this with a-b
    void sub(const Vector3 &a,const Vector3 &b);

    /// @brief get the square length
    double length2() const;
    /// @brief get the length
    double length() const;
    /// returns the distance between this and a
    double distance(const Vector3 &a) const;
    /// returns the squared distance between this and a
    double distance2(const Vector3 &a) const;


    /// @brief this is set with the normalized vector.
    const Vector3 &normalize();
    /// @brief this is set with k*this
    void scale(double k);
    /// @brief this is set with (this.x*kx,this.y*ky,this.z*kz)
    void scale(double kx,double ky,double kz);
    /// @brief this is set with a+k*this (scale *then* add).
    void scaleAdd(double k,const Vector3 &a);
    /// @brief multiply with k : this=k*this (same as scale).
    void mul(double k);
    /// @brief multiply with u : this.x=this.x*u.x, etc
    void mul(const Vector3 &u);

    /// @brief the dot product
    double dot(const Vector3 &a) const;

    /// @brief set this with the cross product a x b
    void cross(const Vector3 &a,const Vector3 &b);
    /// @brief set this with the cross product this x b
    Vector3 cross(const Vector3 &a) const;


    /// @brief multiply then add : this=k*this+t
    void mad(double k,const Vector3 &t);

    /// @brief linear interpolation : this=(1-t)*t1+t*t2
    void mix(const Vector3 &t1,const Vector3 &t2,double t);


    /// @brief set the vector with the mid-point \f$ \frac{n1+n2}{2}\f$
    void mid(const Vector3& n1,const Vector3 &n2);
    /// @brief this is set with (this+n1)/2
    void mid(const Vector3& n1);

    /// @brief the result is "this" turned of angle (radian) around Y
    Vector3 rotationY(float angle) const;

    /// @brief returns the angle between this and u (along vertical; between -PI and PI)
    double angle(const Vector3 &u, const Vector3 &vertical=Vector3(0,0,1)) const;


    /// @brief print the vector
    void print(std::string mesg="") const;

    /// @brief cout the vector
    friend std::ostream& operator <<(std::ostream &s,const Vector3 &t);

    /// @brief return the vector t which is normalized (function)
    friend Vector3 prog3d::normalize(const Vector3 &t);

    friend Vector3 prog3d::operator *(const Vector3 &a,const Vector3 &b);


    /// @brief returns a normal vector (cross product with x, y or z)
    Vector3 anyNormal();

    /// @brief rotate this about axe with angle (radian)
    void rotate(double angle,const Vector3 &axe);

    /// @brief return true if u is (almost) parallel with u
    bool isParallel(const Vector3 &u);

    /// @brief set "this" with (p.x/p.w,p.y/p.w,p.z/p.w) (homogeneous co-ordinate).
    void point(const Vector4 &p);
    void direction(const Vector4 &p);

    /// @brief set x with the min of this.x and a.x (same with y and z).
    void setMinCoordinate(const Vector3 &a);

    /// @brief set x with the max of this.x and a.x (same with y and z).
    void setMaxCoordinate(const Vector3 &a);

    /// @brief set this with b-a (i.e. vector from 2 points)
    void set(const Vector3 &a,const Vector3 &b);

    /// @brief returns the min of the coordinates
    double min(unsigned int *which=NULL);

    /// @brief returns the max of the coordinates
    double max(unsigned int *which=NULL);

};

typedef std::vector<Vector3*> AVector3;

}

#endif // VECTOR3_H




