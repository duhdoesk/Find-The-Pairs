package com.hotwater.findthepairs.presentation.util

import androidx.compose.ui.tooling.preview.Preview


/**
 * Portrait previews
 */

@Preview(
    name = "phone_portrait",
    device = "spec:shape=Normal, width=360, height=640, unit=dp, dpi=480",
    showBackground = true)

@Preview(
    name = "tab_portrait",
    device = "spec:shape=Normal, width=800, height=1280, unit=dp, dpi=480",
    showBackground = true)

annotation class PortraitPreviews


/**
 * Landscape previews
 */

@Preview(
    name = "phone_landscape",
    device = "spec:shape=Normal, width=640, height=360, unit=dp, dpi=480",
    showBackground = true)

@Preview(
    name = "tab_landscape",
    device = "spec:shape=Normal, width=1280, height=800, unit=dp, dpi=480",
    showBackground = true)

annotation class LandscapePreviews