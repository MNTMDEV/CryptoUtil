# CryptoUtil
CryptoUtil为你提供一个包含编码、hash、对称、不对称加密支持的框架

# 包和类说明

## com.mntmdev.cryptoutil.common 通用包

AsymKeyPair 不对称密钥对包装类

EncodingUtil 字符串编码类

KeyUtil 密钥生成类

## com.mntmdev.cryptoutil.core 核心包

AsymSimple 不对称加解密简化版

AsymUtil 不对称加解密通用版

HashUtil 哈希加密类，用于校验

SymSimple 对称加解密简化版，提供的密钥其实是种子，但是可以随意变长

SymUtil 对称加解密通用版，需要提供正确长度的密钥