// To see it working within sbt, do a : 
// run-main ConciseGreedyDependencyParser.package XYZ
// or (if this is the only package with a main() in it) :
// run XYZ  

package object ConciseGreedyDependencyParser {
  case class DefaultList(list:List[String], default:String="") {
    def apply(idx: Int): String = if(idx>=0 || idx<list.length) list(idx) else default
  }


/*
class Perceptron(object):
    def __init__(self, classes=None):
    
        # Each feature gets its own weight vector, so weights is a dict-of-arrays
        self.classes = classes
        self.weights = {}
        
        # The accumulated values, for the averaging. These will be keyed by feature/class tuples
        self._totals = defaultdict(int)
        
        # The last time the feature was changed, for the averaging. Also keyed by feature/class tuples
        # (tstamps is short for timestamps)
        self._tstamps = defaultdict(int)
        
        # Number of instances seen
        self.i = 0

    def predict(self, features):
        '''Dot-product the features and current weights and return the best class.'''
        scores = self.score(features)
        
        # Do a secondary alphabetic sort, for stability
        return max(self.classes, key=lambda clas: (scores[clas], clas))

    def score(self, features):
        all_weights = self.weights
        scores = dict((clas, 0) for clas in self.classes)
        for feat, value in features.items():
            if value == 0:
                continue
            if feat not in all_weights:
                continue
            weights = all_weights[feat]
            for clas, weight in weights.items():
                scores[clas] += value * weight
        return scores

    def update(self, truth, guess, features):       
        def upd_feat(c, f, w, v):
            param = (f, c)
            self._totals[param] += (self.i - self._tstamps[param]) * w
            self._tstamps[param] = self.i
            self.weights[f][c] = w + v

        self.i += 1
        if truth == guess:
            return None
        for f in features:
            weights = self.weights.setdefault(f, {})
            upd_feat(truth, f, weights.get(truth, 0.0), 1.0)
            upd_feat(guess, f, weights.get(guess, 0.0), -1.0)

    def average_weights(self):
        for feat, weights in self.weights.items():
            new_feat_weights = {}
            for clas, weight in weights.items():
                param = (feat, clas)
                total = self._totals[param]
                total += (self.i - self._tstamps[param]) * weight
                averaged = round(total / float(self.i), 3)
                if averaged:
                    new_feat_weights[clas] = averaged
            self.weights[feat] = new_feat_weights

    def save(self, path):
        print "Saving model to %s" % path
        pickle.dump(self.weights, open(path, 'w'))

    def load(self, path):
        self.weights = pickle.load(open(path))


def train(parser, sentences, nr_iter):
    parser.tagger.start_training(sentences)
    for itn in range(nr_iter):
        corr = 0; total = 0
        random.shuffle(sentences)
        #for words, gold_tags, gold_parse, gold_label in sentences:
        for words, gold_tags, gold_parse in sentences:
            corr += parser.train_one(itn, words, gold_tags, gold_parse)
            if itn < 5:
                parser.tagger.train_one(words, gold_tags)
            total += len(words)
        print itn, '%.3f' % (float(corr) / float(total))
        if itn == 4:  ## Why now?
            parser.tagger.model.average_weights()
    print 'Averaging weights'
    parser.model.average_weights()


def read_conll_mdda(loc):
    print "read_conll_mdda(%s)" % (loc, )
    for sent_str in open(loc).read().strip().split('\n\n'):
        lines = [line.split() for line in sent_str.split('\n')]
        
        words = DefaultList('')
        tags  = DefaultList('')
        heads = [None]
        for i, (word, pos, head) in enumerate(lines):
            #print "%d = %s" % (i, word)
            words.append(intern(word))
            #words.append(intern(normalize(word)))
            tags.append(intern(pos))
            heads.append(int(head) if head != '0' else len(lines) + 1) # mdda : don't increment our file...
        pad_tokens(words)
        pad_tokens(tags)
        #print "END OF SENTENCE"
        yield words, tags, heads


def pad_tokens(tokens):
    tokens.insert(0, '<start>')
    tokens.append('ROOT')


def learn_mdda(model_dir, train_loc, load_if_exists=False):
    if not os.path.exists(model_dir):
        os.mkdir(model_dir)
    
    random.seed(04)   # Has some successes, the failure on assert(gold_moves)

    parser = Parser(load=load_if_exists)
    sentences = list()
    for f in [f for f in os.listdir(train_loc) if os.path.isfile(os.path.join(train_loc, f)) and f.endswith(".dp")]:
        sentences.extend(list(read_conll_mdda(os.path.join(train_loc, f))))
        #break # Just 1 set of sentences to start
    #print sentences
    train(parser, sentences, nr_iter=50)
    parser.save()
    
    return 
  
*/  

  def main(args: Array[String]) {
    //args.zipWithIndex map { t => println(s"arg[${t._2}] = '${t._1}'") }
    if(args.contains("learn")) {
      // learn_mdda("models", "/home/andrewsm/nltk_data/corpora/dependency_treebank/")
    }
    
    printf("Usage :\nrun {learn}\n")
  }
}
