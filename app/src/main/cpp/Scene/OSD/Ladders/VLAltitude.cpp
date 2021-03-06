//
// Created by Constantin on 8/20/2018.
//

#include "VLAltitude.h"
#include <SettingsOSDStyle.h>

VLAltitude::VLAltitude(const VLAltitude::Options& options,const SettingsOSDStyle& settingsOSDStyle,const BasicGLPrograms &basicGLPrograms, BatchingManager &batchingManager,
                     const TelemetryReceiver &telemetryReceiver) : AVerticalLadder(settingsOSDStyle,
        basicGLPrograms, batchingManager, telemetryReceiver, false,20),
        mOptions(options){
}

//float val=50;
void VLAltitude::updateGL() {
    float heightMeters;
    const UAVTelemetryData& uav_td= mTelemetryReceiver.getUAVTelemetryData();
    if(mOptions.sourceVal== BARO){
        heightMeters=uav_td.AltitudeBaro_m;
    }else{
        heightMeters=uav_td.AltitudeGPS_m;
    }
    verticalLadderValue=heightMeters;
    //val-=0.2f;
    //verticalLadderValue=val;
    AVerticalLadder::updateGL();
}

IPositionable::Rect2D VLAltitude::calculatePosition(const IPositionable::Rect2D &osdOverlay,const bool stereo) {
    float percentageVideoY;
    float GLOBAL_SCALE;
    if(stereo){
        percentageVideoY=PERCENTAGE_VIDEO_Y_STEREO;
        GLOBAL_SCALE=settingsOSDStyle.OSD_STEREO_GLOBAL_SCALE;
    }else{
        percentageVideoY=PERCENTAGE_VIDEO_Y_MONO;
        GLOBAL_SCALE=settingsOSDStyle.OSD_MONO_GLOBAL_SCALE;
    }
    float height=osdOverlay.mHeight*percentageVideoY*(mOptions.scale*0.01f)*(GLOBAL_SCALE*0.01f);
    float width=height*RATIO;
    // bound to the right side
    //float x=osdOverlay.mX+osdOverlay.mWidth* OFFSET_VIDEO_X_LEFT_OR_RIGHT;
    float x=osdOverlay.mX+osdOverlay.mWidth-(osdOverlay.mWidth* OFFSET_VIDEO_X_LEFT_OR_RIGHT)-width;
    float y=osdOverlay.mY+(osdOverlay.mHeight-height)/2.0f;
    return {x,y,width,height};
}
