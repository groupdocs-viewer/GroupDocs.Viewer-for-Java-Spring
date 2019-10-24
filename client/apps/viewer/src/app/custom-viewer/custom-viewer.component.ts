import {Component} from '@angular/core';
import {ViewerAppComponent, ViewerConfigService, ViewerService} from '@groupdocs.examples.angular/viewer';
import {
  ModalService,
  UploadFilesService,
  NavigateService,
  ZoomService,
  PagePreloadService,
  RenderPrintService,
  PasswordService,
  WindowService,
  LoadingMaskService
} from '@groupdocs.examples.angular/common-components';

@Component({
  selector: 'client-custom-viewer',
  templateUrl: './custom-viewer.component.html',
  styleUrls: ['./custom-viewer.component.css']
})
export class CustomViewerComponent extends ViewerAppComponent {
  public loading;

  constructor(_viewerService: ViewerService, _modalService: ModalService, configService: ViewerConfigService, uploadFilesService: UploadFilesService, _navigateService: NavigateService, _zoomService: ZoomService, pagePreloadService: PagePreloadService, _renderPrintService: RenderPrintService, passwordService: PasswordService, _windowService: WindowService,
              _loadingMaskService: LoadingMaskService) {
    super(_viewerService, _modalService, configService, uploadFilesService, _navigateService, _zoomService, pagePreloadService, _renderPrintService, passwordService, _windowService, _loadingMaskService);

    /**
     *  Put your document retrieval logic here
     *  this example:
     *     1. extracts url from query string
     *     2. downloads document
     *     3. opens downloaded file
     */
    const params = new URLSearchParams(window.location.search);
    const url = params.get('url');
    if (url) {
      const filename = url.split('/').pop();
      this.loading = true;
      _viewerService.upload(null, url, false).subscribe(() => {
        this.loading = false;
        this.selectFile(filename, '', '');
      });
    }
  }
}

