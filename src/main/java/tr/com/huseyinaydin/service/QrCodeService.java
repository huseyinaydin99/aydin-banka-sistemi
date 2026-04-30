package tr.com.huseyinaydin.service;

public interface QrCodeService {
    byte[] generateQrCode(String text, int width, int height);
}
