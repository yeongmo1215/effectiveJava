# ITEM 8 finalizer 와 cleaner 사용을 피하라

cleaner(자바 8까지는 finalizer)는 안전망 혁할이나 중요하지 않은 네이티브 자원 회수용으로만 사용하자. 물론 이런 경우라도 불확실성과 성능 저하에 주의해야 한다.