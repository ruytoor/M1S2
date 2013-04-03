#ifndef ENGINEBOITE_H
#define ENGINEBOITE_H

#include "BoxList.h"
#include <ctime>
#include <cstdlib>
#include "Vector3.h"
#include "Plane.h"

/**
@file
@author F. Aubert
@brief opérations sur OBB pour simu physique (collision+impulsion)

*/

class EngineBox {
  public:
    EngineBox();
    virtual ~EngineBox();

    void boxList(BoxList *b);


    void update();
    void euler(double dt);

    void step(double dt);

    void computeForce();
    void collisionPlane();
    void interCollision();
    void applyCorrection();


    void addVelocityCorrec(const prog3d::Vector3 &v);
    void addPositionCorrec(const prog3d::Vector3 &p);
    void addAngularVelocityCorrec(double w);

    void velocityCorrection();
    void positionCorrection();
    void angularVelocityCorrection();

    void activeCursor(const prog3d::Vector3 &p);
    void motionCursor(const prog3d::Vector3 &p);
    void disableCursor();

    void add(prog3d::Plane *p);

    void draw();
    void drawPlane2D();

    bool isCursorActive();



  protected:
  private:
  BoxList *_boxList;
  clock_t _start;
  double _dt;


  bool _cursorActive;
  prog3d::Vector3 _cursor;

  prog3d::Vector3 _forceInsert;

  std::vector<prog3d::Plane *> _planeList;


};

#endif // ENGINEBOITE_H

