package br.jus.tse.distribuicao_urnas.controller;

import br.jus.tse.distribuicao_urnas.domain.Localizacao;
import br.jus.tse.distribuicao_urnas.domain.ZonaEleitoral;
import br.jus.tse.distribuicao_urnas.model.LocalVotacaoDTO;
import br.jus.tse.distribuicao_urnas.repos.LocalizacaoRepository;
import br.jus.tse.distribuicao_urnas.repos.ZonaEleitoralRepository;
import br.jus.tse.distribuicao_urnas.service.LocalVotacaoService;
import br.jus.tse.distribuicao_urnas.util.WebUtils;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/localVotacaos")
public class LocalVotacaoController {

    private final LocalVotacaoService localVotacaoService;
    private final ZonaEleitoralRepository zonaEleitoralRepository;
    private final LocalizacaoRepository localizacaoRepository;

    public LocalVotacaoController(final LocalVotacaoService localVotacaoService,
            final ZonaEleitoralRepository zonaEleitoralRepository,
            final LocalizacaoRepository localizacaoRepository) {
        this.localVotacaoService = localVotacaoService;
        this.zonaEleitoralRepository = zonaEleitoralRepository;
        this.localizacaoRepository = localizacaoRepository;
    }

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("zonaEleitoralValues", zonaEleitoralRepository.findAll().stream().collect(
                Collectors.toMap(ZonaEleitoral::getId, ZonaEleitoral::getNome)));
        model.addAttribute("localizacaoValues", localizacaoRepository.findAll().stream().collect(
                Collectors.toMap(Localizacao::getId, Localizacao::getId)));
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("localVotacaos", localVotacaoService.findAll());
        return "localVotacao/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("localVotacao") final LocalVotacaoDTO localVotacaoDTO) {
        return "localVotacao/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("localVotacao") @Valid final LocalVotacaoDTO localVotacaoDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "localVotacao/add";
        }
        localVotacaoService.create(localVotacaoDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("localVotacao.create.success"));
        return "redirect:/localVotacaos";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Long id, final Model model) {
        model.addAttribute("localVotacao", localVotacaoService.get(id));
        return "localVotacao/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable final Long id,
            @ModelAttribute("localVotacao") @Valid final LocalVotacaoDTO localVotacaoDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "localVotacao/edit";
        }
        localVotacaoService.update(id, localVotacaoDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("localVotacao.update.success"));
        return "redirect:/localVotacaos";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Long id, final RedirectAttributes redirectAttributes) {
        localVotacaoService.delete(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("localVotacao.delete.success"));
        return "redirect:/localVotacaos";
    }

}
