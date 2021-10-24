package com.geodwarf.apollo.models;

    public class Point {
        private String x ;
        private String y ;

        public Point(String x, String y){
            this.x = x;
            this.y = y;
        }
        public String pointId;

        public String getX() {
            return x;
        }

        public String getY() {
            return y;
        }

        public void setX(String x) {
            this.x = x;
        }

        public void setY(String y) {
            this.y = y;
        }

        public void setPointId(String pointId) {
            this.pointId = pointId;
        }
    }

