#pragma once

#include <inttypes.h>
#include <avr/pgmspace.h>

#define PGM_IP const int8_t *

class Shape 
{
private:
    int scale;
    int angle;
    int ox, oy;

protected:
    virtual int GetXYZ(uint8_t n, int* x, int* y, int* z) = 0;
public:
    void SetTransform(int ox, int oy, int scale, uint8_t angle);
    void Trace(void);
};

class ContiguousShape : public Shape
{
private:
    PGM_IP data;
    uint8_t npoints;
protected:
    virtual int GetXYZ(uint8_t n, int* x, int* y, int* z);
public:
    ContiguousShape(PGM_IP data, int npoints);
};

extern ContiguousShape box;
extern ContiguousShape star;

#define SINSUBDIVS 16

class SinShape : public Shape
{
private:
    uint8_t npoints;
    uint8_t step;

public:
    void SetHalfPeriods(uint8_t n) { 
        npoints = n * SINSUBDIVS; 
        step = 256/npoints;
    }

protected:
    virtual int GetXYZ(uint8_t n, int* x, int* y, int* z);
};

extern SinShape sinus;
