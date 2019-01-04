# Removed --with-pm=gforker from EXTRA_OECONF

EXTRA_OECONF = "--enable-debuginfo \
    --enable-fast \
    --enable-shared  \
    --disable-rpath \
    --disable-f77 \
    --disable-fc \
    --disable-fortran \
    --disable-cxx \
    BASH_SHELL='${USRBINPATH}/env bash' \
    PERL='${USRBINPATH}/env perl' \
"
