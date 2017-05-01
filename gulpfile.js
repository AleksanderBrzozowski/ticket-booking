var gulp = require('gulp');
var inject = require('gulp-inject');
var watch = require('gulp-watch');
var jade = require('gulp-jade');
var angularFilesort = require('gulp-angular-filesort');

var webDir = 'src/main/resources/static/';
var jsDir = webDir + 'app/**/*.js';
var jadeDir = webDir + '**/*.jade';

gulp.task('index', function () {
    var target = gulp.src(webDir + 'index.html');
    var sources = gulp.src(jsDir)
        .pipe(angularFilesort());

    return target.pipe(inject(sources, {relative: true}))
        .pipe(gulp.dest(webDir));
});

gulp.task('dev', ['jade', 'index'], function () {
    gulp.watch(jadeDir, ['jade']);
    gulp.watch(jsDir, ['index'])
});

gulp.task('jade', function () {
    return gulp.src(jadeDir)
        .pipe(jade({pretty: true}))
        .pipe(gulp.dest(webDir));
});

gulp.task('default', ['jade', 'index']);