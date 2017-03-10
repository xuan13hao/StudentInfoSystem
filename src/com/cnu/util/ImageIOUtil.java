package com.cnu.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.MemoryImageSource;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import javax.imageio.ImageIO;
import org.apache.log4j.Logger;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
* ͼƬ����������
*/

public class ImageIOUtil {
    private static Logger logger = Logger.getLogger(ImageIOUtil.class);

    /**
     * �ȱ����ź�Ŀ��
     *
     * @param srcWidth
     *            ԭͼƬ���
     * @param srcHeight
     *            ԭͼƬ�߶�
     * @param destWidth
     *            Ŀ��ͼƬ���
     * @param destHeight
     *            Ŀ��ͼƬ���
     * @return
     */
    public static int[] getWH(int srcWidth, int srcHeight, int destWidth,int destHeight) 
    {
        int[] wh = new int[2];
        int w = destWidth;
        int h = destHeight;
        int sw = srcWidth;
        int sh = srcHeight;
        if (w > 0 && h > 0) {
            double sScale = (double) sw / sh;
            double nScale = (double) w / h;
            if (sScale >= nScale && sw > w) {
                sw = w;
                sh = (int) (sw / sScale);
            } else if (sh > h) {
                sh = h;
                sw = (int) (sh * sScale);
            }
        }
        wh[0] = sw;
        wh[1] = sh;
        return wh;
    }

    /**
     * ǿ�ư��տ������
     *
     * @param w
     * @param h
     * @param filePath
     * @param ins
     * @return
     */
    public static boolean writeFix(int w, int h, String filePath,
            InputStream ins) {
        boolean ret = false;
        FileOutputStream out = null;
        try {
            File f = new File(filePath);
            if (!f.getParentFile().exists())
                f.getParentFile().mkdirs();
            out = new FileOutputStream(filePath); // ������ļ���
            ret = ImageIOUtil.write(w, h, out, ins, BufferedImage.TYPE_INT_RGB,
                    false);
        } catch (Exception e) {
            ret = false;
            logger.error(ImageIOUtil.class, e);
        }
        return ret;
    }

    /**
     *
     * @param w
     * @param h
     * @param out
     * @param ins
     * @param type
     * @param isFix
     * @return
     */
    public static boolean write(int w, int h, OutputStream out,
            InputStream ins, int type, boolean isFix) {
        boolean ret = false;

        try {
            // ����Image����
            BufferedImage src = ImageIO.read(ins);
            int sw = src.getWidth(null);
            int sh = src.getHeight(null);
            if (isFix) {
                int[] wh = getWH(sw, sh, w, h);
                w = wh[0];
                h = wh[1];
            }
            w = w > 0 ? w : sw;
            h = h > 0 ? h : sh;
            // �õ���С���ͷ��
            Image scaled = src.getScaledInstance(w, h, Image.SCALE_SMOOTH);
            // ������С/�Ŵ���ͼ
            BufferedImage thumbnail = new BufferedImage(w, h, type);
            thumbnail.getGraphics().drawImage(scaled, 0, 0, null);
            // ʹ��JPEGѹ��������Ϊ100%
            // ? ����������Sun�İ���Ҫ��Ҫʹ�õ������İ�
           //JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
           // JPEGEncodeParam param = encoder
           //         .getDefaultJPEGEncodeParam(thumbnail);
           // param.setQuality(0.9f, false);
            // JPEG����
            //encoder.encode(thumbnail);
            ret = true;
        } catch (IOException e) {
            ret = false;
            logger.error(ImageIOUtil.class, e);
        } finally {
            try {
                if (ins != null)
                    ins.close();
                if (out != null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    /**
     *
     * @param w
     * @param h
     * @param out
     * @param ins
     * @param src
     * @param type
     * @param isFix
     * @return
     */
    public static boolean write(int w, int h, OutputStream out,
            InputStream ins, BufferedImage src, int type, boolean isFix) {
        boolean ret = false;

        try {
            // ����Image����
            if (src == null) {
                src = ImageIO.read(ins);
            }
            int sw = src.getWidth(null);
            int sh = src.getHeight(null);
            if (isFix) {
                int[] wh = getWH(sw, sh, w, h);
                w = wh[0];
                h = wh[1];
            }
            w = w > 0 ? w : sw;
            h = h > 0 ? h : sh;
            // �õ���С���ͷ��
            Image scaled = src.getScaledInstance(w, h, Image.SCALE_SMOOTH);
            // ������С/�Ŵ���ͼ
            BufferedImage thumbnail = new BufferedImage(w, h, type);
            thumbnail.getGraphics().drawImage(scaled, 0, 0, null);
            // ʹ��JPEGѹ��������Ϊ100%
            // ? ����������Sun�İ���Ҫ��Ҫʹ�õ������İ�
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            JPEGEncodeParam param = encoder
                    .getDefaultJPEGEncodeParam(thumbnail);
            param.setQuality(1.0f, false);
            // JPEG����
            encoder.encode(thumbnail);
            ret = true;
        } catch (IOException e) {
            ret = false;
            logger.error(ImageIOUtil.class, e);
        } finally {
            try {
                if (ins != null)
                    ins.close();
                if (out != null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    /**
     *
     * @param w
     *            ͼƬ���
     * @param h
     *            ͼƬ����
     * @param filePath
     *            ���ͼƬ���ļ�·��
     * @param in
     *            ͼƬ�Ķ�������
     * @return
     */
    public static boolean write(int w, int h, String filePath, InputStream in) {
        boolean ret = false;
        FileOutputStream out = null;
        try {
            File f = new File(filePath);
            if (!f.getParentFile().exists())
                f.getParentFile().mkdirs();
            out = new FileOutputStream(filePath); // ������ļ���

            ret = write(w, h, out, in, BufferedImage.TYPE_INT_RGB, true);
        } catch (IOException e) {
            ret = false;
            logger.error(ImageIOUtil.class, e);
        }
        return ret;
    }

    /**
     * ѹ��ͼƬ
     *
     * @param w
     *            ���
     * @param h
     *            �߶�
     * @param filePath
     *            �����ļ�·��
     * @param in
     *            ����ͼƬ��
     * @param src
     *            ����ͼƬ
     * @return
     */
    public static boolean write(int w, int h, String filePath, InputStream in,
            BufferedImage src) {
        boolean ret = false;
        FileOutputStream out = null;
        try {
            File f = new File(filePath);
            if (!f.getParentFile().exists())
                f.getParentFile().mkdirs();
            out = new FileOutputStream(filePath); // ������ļ���

            ret = write(w, h, out, in, src, BufferedImage.TYPE_INT_RGB, true);
        } catch (IOException e) {
            ret = false;
            logger.error(ImageIOUtil.class, e);
        }
        return ret;
    }

    /**
     * ��ɫ��ͼƬ
     *
     * @param w
     *            ͼƬ���
     * @param h
     *            ͼƬ����
     * @param filePath
     *            ���ͼƬ���ļ�·��
     * @param in
     *            ͼƬ�Ķ�������
     * @return
     */
    public static boolean createGrayImage(int w, int h, String filePath,
            InputStream in) {
        boolean ret = false;
        FileOutputStream out = null;
        try {
            File f = new File(filePath);
            if (!f.getParentFile().exists())
                f.getParentFile().mkdirs();
            out = new FileOutputStream(filePath); // ������ļ���
            ret = write(w, h, out, in, BufferedImage.TYPE_BYTE_GRAY, false);
        } catch (IOException e) {
            ret = false;
            logger.error(ImageIOUtil.class, e);

        }
        return ret;
    }

    /**
     *
     * @param w
     *            ͼƬ���
     * @param h
     *            ͼƬ����
     * @param filePath
     *            ���ͼƬ���ļ�·��
     * @param imageFile
     *            ͼƬ�ļ�
     * @return
     */
    public static boolean write(int w, int h, String filePath, File imageFile) {
        boolean ret = false;
        try {
            // ����Image����
            ret = write(w, h, filePath, new FileInputStream(imageFile));
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(ImageIOUtil.class, e);
        }
        return ret;
    }

    /**
     *
     * @param w
     *            ͼƬ���
     * @param h
     *            ͼƬ����
     * @param filePath
     *            ���ͼƬ���ļ�·��
     * @param url
     *            ͼƬ���ڵ�����·��
     * @return
     */
    public static boolean write(int w, int h, String filePath, URL url) {
        boolean ret = false;
        try {
            // ����Image����
            ret = write(w, h, filePath, url.openStream());
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(ImageIOUtil.class, e);
        }
        return ret;
    }

    /**
     *
     * @param w
     *            ͼƬ���
     * @param h
     *            ͼƬ����
     * @param out
     *            ���ͼƬ��
     * @param url
     *            ͼƬ�����ַ
     * @return
     */
    public static boolean write(int w, int h, OutputStream out, URL url) {
        boolean ret = false;
        try {
            // ����Image����
            ret = write(w, h, out, url.openStream(),
                    BufferedImage.TYPE_INT_RGB, true);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(ImageIOUtil.class, e);
        }
        return ret;
    }

    /**
     * ͼƬ�����ѹ��
     *
     * @param w
     * @param h
     * @param filePath
     * @param in
     * @return
     */
    public static boolean createImage(int w, int h, String filePath,
            InputStream in) {
        boolean ret = false;
        try {
            // ����Image����
            ret = write(w, h, filePath, in);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(ImageIOUtil.class, e);
        }
        return ret;
    }

    /**
     *
     * @param w
     *            ͼƬ���
     * @param h
     *            ͼƬ����
     * @param filePath
     *            ���ͼƬ���ļ�·��
     * @param BufferedImage
     *            ͼƬ
     * @return
     */
    public static boolean write(int w, int h, String filePath, BufferedImage src) {
        boolean ret = false;
        FileOutputStream out = null;
        try {
            File f = new File(filePath);
            if (!f.getParentFile().exists())
                f.getParentFile().mkdirs();
            // ����Image����
            int sw = src.getWidth(null);
            int sh = src.getHeight(null);
            int[] wh = getWH(sw, sh, w, h);
            w = wh[0];
            h = wh[1];
            // �õ���С���ͷ��
            Image scaled = src.getScaledInstance(w, h, Image.SCALE_SMOOTH);
            // ������С/�Ŵ���ͼ
            BufferedImage thumbnail = new BufferedImage(w, h,
                    BufferedImage.TYPE_INT_RGB);
            thumbnail.getGraphics().drawImage(scaled, 0, 0, null);
            // ʹ��JPEGѹ��������Ϊ100%
            // ? ����������Sun�İ���Ҫ��Ҫʹ�õ������İ�
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            JPEGEncodeParam param = encoder
                    .getDefaultJPEGEncodeParam(thumbnail);
            param.setQuality(1.0f, false);
            encoder.setJPEGEncodeParam(param);
            // JPEG����
            encoder.encode(thumbnail);
            ret = true;
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(ImageIOUtil.class, e);
        }
        return ret;
    }

    /**
     * ʵ��ͼ��ĵȱ�����
     *
     * @param source
     * @param targetW
     * @param targetH
     * @return
     */
    private static BufferedImage resize(BufferedImage source, int targetW,
            int targetH) {
        // targetW��targetH�ֱ��ʾĿ�곤�Ϳ�
        int type = source.getType();
        BufferedImage target = null;
        double sx = (double) targetW / source.getWidth();
        double sy = (double) targetH / source.getHeight();
        // ������ʵ����targetW��targetH��Χ��ʵ�ֵȱ����š��������Ҫ�ȱ�����
        // �������if else���ע�ͼ���
        if (sx < sy) {
            sx = sy;
            targetW = (int) (sx * source.getWidth());
        } else {
            sy = sx;
            targetH = (int) (sy * source.getHeight());
        }
        if (type == BufferedImage.TYPE_CUSTOM) { // handmade
            ColorModel cm = source.getColorModel();
            WritableRaster raster = cm.createCompatibleWritableRaster(targetW,
                    targetH);
            boolean alphaPremultiplied = cm.isAlphaPremultiplied();
            target = new BufferedImage(cm, raster, alphaPremultiplied, null);
        } else
            target = new BufferedImage(targetW, targetH, type);
        Graphics2D g = target.createGraphics();
        // smoother than exlax:
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
        g.dispose();
        return target;
    }

    public static boolean resizeWrite(int nw, int nh, String filePath,
            InputStream in) {
        boolean ret = false;
        FileOutputStream out = null;
        try {
            BufferedImage srcImage = ImageIO.read(in);
            // ԭͼ�Ĵ�С
            if (nw > 0 || nh > 0) {
                srcImage = resize(srcImage, nw, nh);
            }
            out = new FileOutputStream(filePath);
            ret = true;
            ImageIO.write(srcImage, "jpg", out);
        } catch (IOException e) {
            ret = false;
            logger.error(ImageIOUtil.class, e);
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ret;

    }

    /**
     * BMPת����JPG��ʽ
     * @param bpmFilePath
     * @param jpgFilePath
     * @return
     */
    public static boolean bmpToJpg(String bpmFilePath, String jpgFilePath) {
        boolean ret = false;
        FileInputStream fs = null;
        FileOutputStream out = null;
        try {
            fs = new FileInputStream(bpmFilePath);
            int bflen = 14;
            byte bf[] = new byte[bflen];
            fs.read(bf, 0, bflen); // ��ȡ14�ֽ�BMP�ļ�ͷ
            int bilen = 40;
            byte bi[] = new byte[bilen];
            fs.read(bi, 0, bilen); // ��ȡ40�ֽ�BMP��Ϣͷ

            // ��ȡһЩ��Ҫ����
            int nwidth = (((int) bi[7] & 0xff) << 24) // Դͼ���
                    | (((int) bi[6] & 0xff) << 16)
                    | (((int) bi[5] & 0xff) << 8) | (int) bi[4] & 0xff;
            int nheight = (((int) bi[11] & 0xff) << 24) // Դͼ�߶�
                    | (((int) bi[10] & 0xff) << 16)
                    | (((int) bi[9] & 0xff) << 8) | (int) bi[8] & 0xff;
            // λ��
            int nbitcount = (((int) bi[15] & 0xff) << 8) | (int) bi[14] & 0xff;
            // Դͼ��С
            int nsizeimage = (((int) bi[23] & 0xff) << 24)
                    | (((int) bi[22] & 0xff) << 16)
                    | (((int) bi[21] & 0xff) << 8) | (int) bi[20] & 0xff;
            logger.info("��:" + nwidth + " ��:" + nheight + " λ��:" + nbitcount
                        + " Դͼ��С:" + nsizeimage);

            Image image = null; // ����һ��Ŀ��ͼ
            // ��24λBMP���н���
            if (nbitcount == 24) {
                int npad = (nsizeimage / nheight) - nwidth * 3;
                int ndata[] = new int[nheight * nwidth];
                byte brgb[] = new byte[(nwidth + npad) * 3 * nheight];
                fs.read(brgb, 0, (nwidth + npad) * 3 * nheight);
                int nindex = 0;
                for (int j = 0; j < nheight; j++) {
                    for (int i = 0; i < nwidth; i++) {
                        ndata[nwidth * (nheight - j - 1) + i] = (255 & 0xff) << 24
                                | (((int) brgb[nindex + 2] & 0xff) << 16)
                                | (((int) brgb[nindex + 1] & 0xff) << 8)
                                | (int) brgb[nindex] & 0xff;
                        nindex += 3;
                    }
                    nindex += npad;
                }
                Toolkit kit = Toolkit.getDefaultToolkit();
                image = kit.createImage(new MemoryImageSource(nwidth, nheight,
                        ndata, 0, nwidth));
                logger.info("�� BMP�õ�ͼ��image");
            } else {
                logger.error("����24λBMP��ʧ�ܣ�");
                return false;
            }
            fs.close();

            // ��ʼ����ͼ��ѹ������image������в�����
            int wideth = image.getWidth(null); // �õ�Դͼ��
            int height = image.getHeight(null); // �õ�Դͼ��
            BufferedImage tag = new BufferedImage(wideth, height, BufferedImage.TYPE_INT_RGB);
            tag.getGraphics().drawImage(image, 0, 0, wideth, height,null); // ������С���ͼ
            out = new FileOutputStream(jpgFilePath); // ������ļ���
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(tag); // ����JPEG����
           
            ret = true;
        } catch (Exception e) {
            logger.error(e);
            ret = false;
        }finally {
            try {
                if(fs != null) {
                    fs.close();// �ر�������
                }
                if(out != null) {
                    out.close(); // �ر������
                }
            } catch (IOException e) {
                logger.error("close error:" + e.getMessage());
            }
        }
        return ret;

    }

    public static void main(String args[]) {
        try {
            File file = new File("D:\\\\test.jpg");
            InputStream in = new FileInputStream(file);
            BufferedImage src = ImageIO.read(in);
            ImageIOUtil.write(400, 200, "d:\\\\test1.jpg", in, src);
        } catch (Exception e) {
            e.printStackTrace();
        }
       

    }
} 