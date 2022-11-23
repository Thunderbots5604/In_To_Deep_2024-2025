package org.firstinspires.ftc.teamcode.koawalib.vision

import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.subsystem.Subsystem
import com.asiankoala.koawalib.subsystem.vision.KWebcam
import org.openftc.apriltag.AprilTagDetection

class Webcam(val device: KWebcam, private val pipeline: SleevePipeline) : Subsystem() {

    var LEFT = 1
    var MIDDLE = 2
    var RIGHT = 3
    var tagOfInterest: AprilTagDetection? = null

    fun start() {
        val currentDetections: ArrayList<AprilTagDetection> = pipeline.latestDetections
        if (currentDetections.size != 0) {
            var tagFound = false
            for (tag in currentDetections) {
                if (tag.id == LEFT || tag.id == MIDDLE || tag.id == RIGHT) {
                    tagOfInterest = tag
                    tagFound = true
                    break
                }
            }
            if (tagFound) {
                Logger.addTelemetryLine("Tag of interest is in sight!\n\nLocation data:")
                tagToTelemetry(tagOfInterest)
            } else {
                Logger.addTelemetryLine("Don't see tag of interest :(")
                if (tagOfInterest == null) {
                    Logger.addTelemetryLine("(The tag has never been seen)")
                } else {
                    Logger.addTelemetryLine("\nBut we HAVE seen the tag before; last seen at:")
                    tagToTelemetry(tagOfInterest)
                }
            }
        } else {
            Logger.addTelemetryLine("Don't see tag of interest :(")
            if (tagOfInterest == null) {
                Logger.addTelemetryLine("(The tag has never been seen)")
            } else {
                Logger.addTelemetryLine("\nBut we HAVE seen the tag before; last seen at:")
                tagToTelemetry(tagOfInterest)
            }
        }
    }

    fun update() {
        if (tagOfInterest != null) {
            Logger.addTelemetryLine("Tag snapshot:\n")
            tagToTelemetry(tagOfInterest)
        } else {
            Logger.addTelemetryLine("No tag snapshot available, it was never sighted during the init loop :(")
        }
    }
    fun tagToTelemetry(detection: AprilTagDetection?) {
        Logger.addTelemetryLine(String.format("\nDetected tag ID=%d", detection!!.id))
        Logger.addTelemetryLine(
            String.format(
                "Translation X: %.2f feet",
                detection.pose.x * FEET_PER_METER
            )
        )
        Logger.addTelemetryLine(
            String.format(
                "Translation Y: %.2f feet",
                detection.pose.y * FEET_PER_METER
            )
        )
        Logger.addTelemetryLine(
            String.format(
                "Translation Z: %.2f feet",
                detection.pose.z * FEET_PER_METER
            )
        )
        Logger.addTelemetryLine(
            String.format(
                "Rotation Yaw: %.2f degrees", Math.toDegrees(
                    detection.pose.yaw
                )
            )
        )
        Logger.addTelemetryLine(
            String.format(
                "Rotation Pitch: %.2f degrees", Math.toDegrees(
                    detection.pose.pitch
                )
            )
        )
        Logger.addTelemetryLine(
            String.format(
                "Rotation Roll: %.2f degrees", Math.toDegrees(
                    detection.pose.roll
                )
            )
        )
    }

    companion object {
        const val FEET_PER_METER = 3.28084
    }
}