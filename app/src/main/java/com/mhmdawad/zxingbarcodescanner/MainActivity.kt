package com.mhmdawad.zxingbarcodescanner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import com.mhmdawad.zxingbarcodescanner.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.launchScanner.setOnClickListener {
            launchScanner()
        }
    }

    private fun launchScanner() {
                val options = ScanOptions()
                    .setOrientationLocked(false)
                    .setCaptureActivity(CustomScannerActivity::class.java)
                    .setCameraId(0)
                    .setBeepEnabled(false)
                    .setBarcodeImageEnabled(true)
                    .setDesiredBarcodeFormats(ScanOptions.QR_CODE)

        barcodeLauncher.launch(options)
    }

    private val barcodeLauncher = registerForActivityResult(
        ScanContract()
    ) { result: ScanIntentResult ->
        if (!result.contents.isNullOrEmpty()) {
            Toast.makeText(this, "Scan Result: ${result.contents}", Toast.LENGTH_SHORT).show()
        }else{
            // CANCELED
        }
    }
}